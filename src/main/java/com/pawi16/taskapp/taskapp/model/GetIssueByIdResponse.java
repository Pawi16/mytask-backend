package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetIssueByIdResponse {
    private String name;
    private String description;
    private String status;
    private LocalDate dueDate;
    private String issueType;
    private boolean isCompleted;
    private String priority;
    private String createdUserId;
    private String parentIssueId;
    private String boardId;
}
