package com.example.demo.dto;

import java.math.BigDecimal;

// Um "record" perfeito para apenas devolver os 3 valores calculados
public record BalanceResponseDTO(
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        BigDecimal balance
) {}