package com.example.Ats.dto;

public record RegisterRequest(
        String name,
        String email,
        String password
) {}
