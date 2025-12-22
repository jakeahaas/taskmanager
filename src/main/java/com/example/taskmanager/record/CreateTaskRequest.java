package com.example.taskmanager.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTaskRequest(
        @NotBlank String title,
        String description,
        @NotNull String status,
        Long assignedUserId
) {}