package com.company.insuranceapp.model.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @JsonIgnore
    private String password;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private LocalDateTime createdDate;
    private Set<RoleResponse> roles;}
