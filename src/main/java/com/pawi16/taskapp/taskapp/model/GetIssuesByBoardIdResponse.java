package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GetIssuesByBoardIdResponse {
    private String issueId;
    private String name;
    private String description;
    private String status;
    private String issueType;
    private boolean isCompleted;
    private boolean isDeleted;
    private LocalDate dueDate;
    private String priority;
    private String createdUserId;
    private List<GetIssuesByBoardIdResponse> childIssues;
}
