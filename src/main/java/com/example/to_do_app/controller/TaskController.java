package com.example.to_do_app.controller;

import com.example.to_do_app.model.User;
import com.example.to_do_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        User user = userService.getCurrentUser();

        return user.getName()+" Task controller is working!";
    }
}
