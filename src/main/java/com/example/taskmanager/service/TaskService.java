package com.example.taskmanager.service;

import java.util.List;

import com.example.taskmanager.record.CreateTaskRequest;
import com.example.taskmanager.record.TaskResponse;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    TaskResponse getTask(Long id);
    List<TaskResponse> getAllTasks();
}