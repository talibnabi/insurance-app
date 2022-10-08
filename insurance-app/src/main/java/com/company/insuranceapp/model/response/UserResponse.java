package com.company.insuranceapp.model.response;


import com.company.insuranceapp.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Long point;
    private Set<RoleResponse> roles;
    private Set<Course> courses;

}
