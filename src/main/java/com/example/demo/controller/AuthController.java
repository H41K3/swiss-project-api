package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder; // IMPORT NOVO
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // IMPORT NOVO
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthenticationDTO;
import com.example.demo.dto.ChangePasswordDTO; // IMPORT NOVO
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.infra.security.TokenService;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        
        User newUser = new User(null, data.login(), encryptedPassword, UserRole.USER);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    // ==========================================
    // ROTA NOVA: TROCA DE SENHA
    // ==========================================
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDTO data) {
        // 1. Pega o usuário que está logado e fez a requisição (baseado no Token)
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();

        // 2. Busca o usuário atualizado no banco de dados para garantir
        // Assumindo que seu repository tem um findById
        User user = repository.findById(loggedInUser.getId()).orElseThrow();

        // 3. Verifica se a senha atual informada bate com a senha criptografada do banco
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(data.currentPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Senha atual incorreta.");
        }

        // 4. Criptografa a nova senha e atualiza no banco
        String encryptedNewPassword = encoder.encode(data.newPassword());
        user.setPassword(encryptedNewPassword); // Atenção: a sua classe User precisa ter o método setPassword()
        repository.save(user);

        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }
}