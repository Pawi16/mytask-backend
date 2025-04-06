package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetMyProfileResponse {
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
}
