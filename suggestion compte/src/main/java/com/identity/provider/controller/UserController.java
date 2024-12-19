package com.identity.provider.controller;

import com.identity.provider.dto.UserDto;
import com.identity.provider.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto.UserResponse> registerUser(@Valid @RequestBody UserDto.RegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto.UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto.UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserDto.UpdateRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
} 