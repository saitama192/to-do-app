package com.example.to_do_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {


    @GetMapping("/test")
    public String test() {
        User userDetails = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();


        return userDetails.getUsername()+" Task controller is working!";
    }
}
