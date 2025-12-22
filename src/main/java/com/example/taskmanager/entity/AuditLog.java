package com.example.taskmanager.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "audit_logs")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog extends BaseEntity {

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private Long entityId;

    @Column(nullable = false)
    private String action;

    private String performedBy;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private Instant timestamp = Instant.now();
}