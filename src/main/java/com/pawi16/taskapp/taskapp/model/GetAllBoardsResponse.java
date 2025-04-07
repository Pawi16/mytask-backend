package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

@Data
public class GetAllBoardsResponse {
    private String id;
    private String title;
    private String createUserFirstname;
    private int issueCount;
}
