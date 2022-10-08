package com.company.insuranceapp.model.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private Long point;
}
