package com.example.demo.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.User;
import com.example.demo.service.TransactionService;
import com.example.demo.service.AuthorizationService;
import com.example.demo.infra.security.TokenService;
import com.example.demo.repository.UserRepository; // 1. Importe o repositório

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService service;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private AuthorizationService authorizationService;

    @MockitoBean // 2. Crie o Mock do UserRepository aqui!
    private UserRepository userRepository;

    @Test
    @WithMockUser
    public void shouldReturnOkWhenGettingAllTransactions() throws Exception {
        Mockito.when(service.getAllTransactions(ArgumentMatchers.any(User.class)))
               .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/transactions"))
               .andExpect(status().isOk());
    }
}