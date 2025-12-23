package com.example.taskmanager.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.entity.UserRole;
import com.example.taskmanager.record.LoginRequest;
import com.example.taskmanager.record.RegisterUserRequest;
import com.example.taskmanager.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterUserRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = User.builder()
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new EntityNotFoundException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new EntityNotFoundException("Invalid credentials");
        }

        return jwtService.generateToken(user);
    }
}