package com.company.insuranceapp.model.request;

import com.company.insuranceapp.model.enumeration.RoleType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleRequest {
    @NotNull
    private RoleType roleType;
}
