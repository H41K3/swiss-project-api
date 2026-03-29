package com.globalwallet.api.dto;

import java.math.BigDecimal;

public record BalanceResponseDTO(
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        BigDecimal balance
) {};