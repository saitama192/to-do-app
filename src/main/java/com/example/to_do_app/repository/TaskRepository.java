package com.example.to_do_app.repository;

import com.example.to_do_app.model.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TodoTask,Long> {
}
