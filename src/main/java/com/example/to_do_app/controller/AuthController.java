package com.example.to_do_app.controller;

import com.example.to_do_app.dto.LoginRequest;
import com.example.to_do_app.dto.UserRegistrationDTO;
import com.example.to_do_app.model.Role;
import com.example.to_do_app.model.User;
import com.example.to_do_app.service.UserService;
import com.example.to_do_app.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody @Valid UserRegistrationDTO userDTO,
                                        @RequestParam(defaultValue = "false") boolean isAdmin) {
        User user = new User();
        user.setName(userDTO.name());
        user.setSurname(userDTO.surname());
        user.setEmail(userDTO.email());
        user.setPhone(userDTO.phone());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setRoles(isAdmin ? Collections.singleton(Role.ADMIN) : Collections.singleton(Role.USER));

        userService.createUser(user, isAdmin);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody @Valid LoginRequest request) {
        try {
            String username = request.username();
            String password = request.password();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = jwtUtil.generateToken(authentication.getName());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }
}