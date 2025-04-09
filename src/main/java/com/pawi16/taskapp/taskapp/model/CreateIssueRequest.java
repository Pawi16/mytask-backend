package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateIssueRequest {
    private String name;
    private String description;
    private LocalDate dueDate;
    private String issueType;      // Enum - IssueType
    private String priority;       // Enum - PriorityType
    private String parentIssueId;    // Optional - if present, this is a sub-issue
    private String boardId;
}
