package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.model.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionRequestDTO(
        
        @NotBlank(message = "A descrição é obrigatória e não pode estar em branco")
        String description,

        @NotNull(message = "O valor da transação é obrigatório")
        BigDecimal amount,

        @NotNull(message = "A data da transação é obrigatória")
        LocalDate transactionDate,

        // NOVO CAMPO: Bloqueia a entrada se o usuário não disser se é receita ou despesa
        @NotNull(message = "O tipo da transação (INCOME ou EXPENSE) é obrigatório")
        TransactionType type
) {}