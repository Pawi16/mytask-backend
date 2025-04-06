package com.pawi16.taskapp.taskapp.model;

import lombok.Data;

@Data
public class GetProfileByIdResponse {
    private String email;
    private String firstName;
    private String lastName;
}
