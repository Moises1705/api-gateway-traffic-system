package com.auth.controller;

import com.auth.dto.LoginRequestDto;
import com.auth.dto.TokenResponseDto;
import com.auth.model.UserEntity;
import com.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints para registro y login de usuarios")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario")
    public ResponseEntity<UserEntity> register(@RequestBody LoginRequestDto request) {
        UserEntity newUser = authService.register(request);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener token JWT")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto request) {
        TokenResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}