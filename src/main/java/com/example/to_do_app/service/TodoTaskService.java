package com.example.to_do_app.service;

import com.example.to_do_app.dto.SaveTaskDTO;
import com.example.to_do_app.model.TodoTask;
import com.example.to_do_app.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class TodoTaskService {
    private TaskRepository taskRepository;
    private UserService userService;

    TodoTaskService(TaskRepository taskRepository, UserService userService){
        this.taskRepository=taskRepository;
        this.userService=userService;
    }

    public TodoTask saveTodoTask(@Valid SaveTaskDTO saveTaskDTO){
        TodoTask task = new TodoTask();
        task.setTaskName(saveTaskDTO.taskName());
        task.setDateAdded( new java.util.Date(System.currentTimeMillis()));
        task.setDueDate(saveTaskDTO.dueDate());
        task.setDescription(saveTaskDTO.description());
        task.setUserId(userService.getCurrentUser().getId());
        return  taskRepository.save(task);
    }
}
