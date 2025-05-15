package com.example.to_do_app.controller;

import com.example.to_do_app.dto.SaveTaskDTO;
import com.example.to_do_app.model.TodoTask;
import com.example.to_do_app.model.User;
import com.example.to_do_app.service.TodoTaskService;
import com.example.to_do_app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TodoTaskController {

    private final UserService userService;
    private final TodoTaskService todoTaskService;

    @GetMapping("/test")
    public String test() {
        User user = userService.getCurrentUser();

        return user.getName()+" Task controller is working!";
    }

    @PostMapping("/save")
    public ResponseEntity<TodoTask> createTask(@RequestBody @Valid SaveTaskDTO saveTaskDTO){
        TodoTask task = todoTaskService.saveTodoTask(saveTaskDTO);
        return ResponseEntity.ok().body(task);
    }
}
