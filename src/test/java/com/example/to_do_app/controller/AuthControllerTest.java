package com.example.to_do_app.controller;

import com.example.to_do_app.dto.LoginRequest;
import com.example.to_do_app.service.UserService;
import com.example.to_do_app.util.JwtUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtil jwtUtil;
    @InjectMocks
    private AuthController authController;


    @Test
    @Disabled("Implementation to be provided")
    void register() {
    }
    @Test
    void loginWithCorrectCredentials() {
        // Arrange
        LoginRequest request = new LoginRequest("test@example.com", "password123");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        UserDetails userDetails = new User(
                "test@example.com",
                "password123",
                Collections.emptyList()
        );

        when(jwtUtil.generateToken("test@example.com")).thenReturn("fake-jwt-token");
        when(authentication.getName()).thenReturn("test@example.com");

        // Act
        ResponseEntity<?> response = authController.login(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> responseMap = (Map<String, String>) response.getBody();
        assertEquals("fake-jwt-token", responseMap.get("token"));
    }

    @Test
    void loginWithInCorrectCredentials() {
        // Arrange
        LoginRequest request = new LoginRequest("wrong@example.com", "incorrectPassword");

        // Configure AuthenticationManager to throw an AuthenticationException
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid username or password"));

        // Act
        try {
            authController.login(request);
            fail("Expected a RuntimeException to be thrown"); // If no exception is thrown, the test fails
        } catch (RuntimeException e) {
            // Assert
            assertEquals("Invalid username or password", e.getMessage());
        }
    }


}