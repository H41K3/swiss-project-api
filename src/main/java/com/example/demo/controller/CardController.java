package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CardRequestDTO;
import com.example.demo.model.Card;
import com.example.demo.model.User;
import com.example.demo.service.CardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.getAllCards(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.getCardById(id, user));
    }

    @PostMapping
    public ResponseEntity<Card> create(@Valid @RequestBody CardRequestDTO dto, @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCard(dto, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @Valid @RequestBody CardRequestDTO dto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.updateCard(id, dto, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        service.deleteCard(id, user);
        return ResponseEntity.noContent().build();
    }
}