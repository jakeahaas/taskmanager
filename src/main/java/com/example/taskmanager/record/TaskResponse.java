package com.example.taskmanager.record;

import java.time.Instant;

import com.example.taskmanager.entity.TaskStatus;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Long assignedUserId,
        Instant createdAt,
        Instant updatedAt
) {}