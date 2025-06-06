package com.example.to_do_app.service;

import com.example.to_do_app.dto.SaveTaskDTO;
import com.example.to_do_app.model.TodoTask;
import com.example.to_do_app.model.User;
import com.example.to_do_app.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoTaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TodoTaskService taskService;


    @Test
    void saveTodoTask_ValidInput_ReturnsSavedTask() {
        Date now = new Date();
        SaveTaskDTO saveTaskDTO = new SaveTaskDTO(
                "Test Task",
                "Test Description",
                now
        );

        User mockUser = new User();
        mockUser.setId(1L);

        // Mock the UserService to return the mock User.
        when(userService.getCurrentUser()).thenReturn(mockUser);

        TodoTask expectedSavedTask = new TodoTask();
        expectedSavedTask.setId(10L);
        expectedSavedTask.setTaskName(saveTaskDTO.taskName());
        expectedSavedTask.setDateAdded(now);
        expectedSavedTask.setDueDate(saveTaskDTO.dueDate());
        expectedSavedTask.setDescription(saveTaskDTO.description());
        expectedSavedTask.setUserId(1L);

        when(taskRepository.save(any(TodoTask.class))).thenReturn(expectedSavedTask);

        TodoTask actualSavedTask = taskService.saveTodoTask(saveTaskDTO);

        assertEquals(expectedSavedTask.getId(), actualSavedTask.getId());
        assertEquals(expectedSavedTask.getTaskName(), actualSavedTask.getTaskName());
        assertEquals(expectedSavedTask.getDateAdded(), actualSavedTask.getDateAdded());
        assertEquals(expectedSavedTask.getDueDate(), actualSavedTask.getDueDate());
        assertEquals(expectedSavedTask.getDescription(), actualSavedTask.getDescription());
        assertEquals(expectedSavedTask.getUserId(), actualSavedTask.getUserId());
    }

    @Test
    void getId_ValidId_ReturnsTask() {
        long taskId = 1L;
        TodoTask expectedTask = new TodoTask();
        expectedTask.setId(taskId);
        expectedTask.setTaskName("Test Task");
        expectedTask.setDescription("Test Description");
        expectedTask.setDateAdded(new Date());
        expectedTask.setDueDate(new Date());
        expectedTask.setUserId(1L);

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(expectedTask));

        TodoTask actualTask = taskService.getTodoTaskById(taskId);

        assertEquals(expectedTask.getId(), actualTask.getId());
        assertEquals(expectedTask.getTaskName(), actualTask.getTaskName());
    }

    @Test
    void deleteTodoTask_ValidId_DeletesTask() {
        long taskId = 1L;
        TodoTask taskToDelete = new TodoTask();
        taskToDelete.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskToDelete));

        taskService.deleteTodoTask(taskId);

        verify(taskRepository).delete(taskToDelete); // ✅ verify delete was called
    }
}