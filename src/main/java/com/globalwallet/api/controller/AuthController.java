package com.globalwallet.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalwallet.api.dto.AuthenticationDTO;
import com.globalwallet.api.dto.ChangePasswordDTO;
import com.globalwallet.api.dto.LoginResponseDTO;
import com.globalwallet.api.dto.RegisterDTO;
import com.globalwallet.api.dto.UserProfileDTO;
import com.globalwallet.api.infra.security.TokenService;
import com.globalwallet.api.model.User;
import com.globalwallet.api.model.UserRole;
import com.globalwallet.api.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
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
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.cpf()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User();
        newUser.setLogin(data.cpf());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(UserRole.USER);
        newUser.setFullName(data.fullName());
        newUser.setEmail(data.email());
        newUser.setPhone(data.phone());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDTO data) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();

        User user = repository.findById(loggedInUser.getId()).orElseThrow();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(data.currentPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Senha atual incorreta.");
        }

        String encryptedNewPassword = encoder.encode(data.newPassword());
        user.setPassword(encryptedNewPassword);
        repository.save(user);

        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getUserProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();

        User user = repository.findById(loggedInUser.getId()).orElseThrow();

        return ResponseEntity.ok(new UserProfileDTO(user.getFullName(), user.getEmail(), user.getPhone()));
    }
}