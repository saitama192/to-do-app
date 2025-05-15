package com.example.to_do_app.dto;

import com.example.to_do_app.model.Status;

import java.util.Date;

public record UpdateTaskDTO(
        String taskName,
        String description,
        Date dueDate,
        Status status
) {
}
