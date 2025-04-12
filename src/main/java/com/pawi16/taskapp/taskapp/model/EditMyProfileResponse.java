package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditMyProfileResponse {
    private String firstName;
    private String lastName;
    private LocalDateTime updatedAt;
    private String message;
}
