package com.globalwallet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank
        String cpf, @NotBlank
        String password) {

}
