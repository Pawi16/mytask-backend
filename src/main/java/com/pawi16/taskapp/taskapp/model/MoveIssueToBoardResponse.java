package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

@Data
public class MoveIssueToBoardResponse {
    private String issueId;
    private String boardId;
}
