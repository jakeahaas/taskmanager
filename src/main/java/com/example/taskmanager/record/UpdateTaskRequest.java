package com.example.taskmanager.record;

import com.example.taskmanager.entity.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskRequest(
        @NotBlank String title,
        String description,
        @NotNull TaskStatus status,
        Long assignedUserId
) {}