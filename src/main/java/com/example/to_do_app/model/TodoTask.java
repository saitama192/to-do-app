package com.example.to_do_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoTask {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    private String taskName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String description;
    private Date dateAdded;
    private Date dueDate;
    @JoinColumn(name="userId", referencedColumnName = "id")
    private long userId;
}
