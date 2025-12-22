package com.example.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanager.entity.AuditLog;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.record.CreateTaskRequest;
import com.example.taskmanager.record.TaskResponse;
import com.example.taskmanager.repository.AuditLogRepository;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {

        User assignedUser = null;
        if (request.assignedUserId() != null) {
            assignedUser = userRepository.findById(request.assignedUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
        }

        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .assignedUser(assignedUser)
                .build();

        Task saved = taskRepository.save(task);

        auditLogRepository.save(
                AuditLog.builder()
                        .entityType("TASK")
                        .entityId(saved.getId())
                        .action("CREATE")
                        .performedBy("SYSTEM")
                        .build()
        );

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        return mapToResponse(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssignedUser() != null ? task.getAssignedUser().getId() : null,
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}