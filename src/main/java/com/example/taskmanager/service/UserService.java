package com.example.taskmanager.service;

import com.example.taskmanager.record.LoginRequest;
import com.example.taskmanager.record.RegisterUserRequest;

public interface UserService {
    void register(RegisterUserRequest request);
    String login(LoginRequest request);
}