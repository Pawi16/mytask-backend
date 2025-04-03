package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
