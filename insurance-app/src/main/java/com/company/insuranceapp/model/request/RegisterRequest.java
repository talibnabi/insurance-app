package com.company.insuranceapp.model.request;

import com.company.insuranceapp.annotation.ValidPassword;
import com.company.insuranceapp.model.enumeration.RoleType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String userName;
    @Email
    @NotNull
    private String email;
    @ValidPassword
    private String password;
    @NotNull
    private RoleType roleType;
}
