package com.example.Ats.controller;
import com.example.Ats.dto.LoginRequest;
import com.example.Ats.dto.RegisterRequest;
import com.example.Ats.service.AuthService;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        return ResponseEntity.ok(Map.of(
                "token", token));
    }

}

// https://accounts.google.com/logout