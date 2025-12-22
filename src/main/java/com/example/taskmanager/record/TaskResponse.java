package com.example.taskmanager.record;

import java.time.Instant;

public record TaskResponse(
        Long id,
        String title,
        String description,
        String status,
        Long assignedUserId,
        Instant createdAt,
        Instant updatedAt
) {}