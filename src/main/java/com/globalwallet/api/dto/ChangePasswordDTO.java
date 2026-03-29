package com.globalwallet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDTO(
    @NotBlank String currentPassword,
    @NotBlank String newPassword
) {}