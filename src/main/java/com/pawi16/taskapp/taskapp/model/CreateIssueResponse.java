package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateIssueResponse {
    private String id;
    private String name;
    private String status;
    private String issueType;
    private boolean isCompleted;
    private String priority;
    private LocalDate dueDate;
    private String boardId;
    private String parentIssueId;
    private LocalDateTime createdAt;
}
