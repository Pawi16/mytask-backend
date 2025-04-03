package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String email;
    private String token;
}
