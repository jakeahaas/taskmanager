package com.example.taskmanager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SECRET;
    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000;

    public JwtService(@Value("${SECRET}") String secret) {
        this.SECRET = secret;
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}