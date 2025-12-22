package com.example.taskmanager.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.taskmanager.entity.TaskStatus;
import com.example.taskmanager.record.CreateTaskRequest;
import com.example.taskmanager.record.TaskResponse;
import com.example.taskmanager.record.UpdateTaskRequest;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    TaskResponse getTask(Long id);
    Page<TaskResponse> getTasks(TaskStatus status, int page, int size);
    List<TaskResponse> getAllTasks();
    TaskResponse updateTask(Long id, UpdateTaskRequest request);
    void deleteTask(Long id);
}