package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDTO(
    @NotBlank String currentPassword,
    @NotBlank String newPassword
) {}