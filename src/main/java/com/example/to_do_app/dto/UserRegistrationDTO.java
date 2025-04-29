package com.example.to_do_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationDTO(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Surname is required") String surname,
        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
        @NotBlank(message = "Phone is required") String phone,
        @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String password
) {}
