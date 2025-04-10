package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EditIssueByIdResponse {
    private String name;
    private String description;
    private String status;
    private LocalDate dueDate;
    private boolean isCompleted;
    private String priority;
    private String parentIssueId;
    private String boardId;
    private LocalDate updatedAt;
}
