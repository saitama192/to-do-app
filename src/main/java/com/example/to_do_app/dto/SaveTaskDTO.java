package com.example.to_do_app.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record SaveTaskDTO(

        @NotBlank(message = "Task Name is required")String taskName,
        String description,
        Date dueDate) {
}
