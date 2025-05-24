package com.example.to_do_app.controller;

import com.example.to_do_app.dto.SaveTaskDTO;
import com.example.to_do_app.dto.UpdateTaskDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<TodoTask> getTaskById(@PathVariable Long id) {
        TodoTask task = todoTaskService.getTodoTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/save")
    public ResponseEntity<TodoTask> createTask(@RequestBody @Valid SaveTaskDTO saveTaskDTO){
        TodoTask task = todoTaskService.saveTodoTask(saveTaskDTO);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        todoTaskService.deleteTodoTask(id);
        return ResponseEntity.ok().body(String.format("Task with id %d deleted successfully", id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TodoTask> updateTask(@PathVariable Long id, @RequestBody @Valid UpdateTaskDTO updateTaskDTO){
        TodoTask task = todoTaskService.updateTodoTask(id, updateTaskDTO);
        return ResponseEntity.ok().body(task);
    }
}
