package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.record.CreateTaskRequest;
import com.example.taskmanager.record.TaskResponse;
import com.example.taskmanager.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(
            @Valid @RequestBody CreateTaskRequest request
    ) {
        return taskService.createTask(request);
    }

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }
}