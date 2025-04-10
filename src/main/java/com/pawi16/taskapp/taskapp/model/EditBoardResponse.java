package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EditBoardResponse {
    private String title;
    private String message;
    private LocalDate updateAt;
}
