package com.example.demo.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.repository.TransactionRepository;

@org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc; // O robô que simula o navegador/Swagger

    @MockitoBean
    private TransactionRepository repository; // Um banco de dados "de mentira" para o teste

    @Test
    public void shouldReturnOkWhenGettingAllTransactions() throws Exception {
        // 1. Prepara o cenário: Ensina o banco de mentira a retornar uma lista vazia
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        // 2. Executa a ação e verifica o resultado: Chama a API e garante que ela responde 200 OK
        mockMvc.perform(get("/api/v1/transactions"))
                .andExpect(status().isOk());
    }
}