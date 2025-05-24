package com.example.to_do_app.service;

import com.example.to_do_app.dto.SaveTaskDTO;
import com.example.to_do_app.dto.UpdateTaskDTO;
import com.example.to_do_app.model.Status;
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
        task.setStatus(Status.PENDING);
        return  taskRepository.save(task);
    }

    public void deleteTodoTask(Long id) {
        TodoTask task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }

    public TodoTask updateTodoTask(Long id, UpdateTaskDTO updateTaskDTO) {
        TodoTask task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (updateTaskDTO.taskName() != null && !updateTaskDTO.taskName().isBlank()) {
            task.setTaskName(updateTaskDTO.taskName());
        }

        if (updateTaskDTO.description() != null && !updateTaskDTO.description().isBlank()) {
            task.setDescription(updateTaskDTO.description());
        }

        if (updateTaskDTO.dueDate() != null) {
            task.setDueDate(updateTaskDTO.dueDate());
        }

        if (updateTaskDTO.status() != null) {
            task.setStatus(updateTaskDTO.status());
        }

        return taskRepository.save(task);
    }

    public TodoTask getTodoTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
