package com.example.to_do_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank @Email String username,
                           @NotBlank String password) {}
