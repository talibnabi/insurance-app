package com.company.insuranceapp.model.response;

import com.company.insuranceapp.model.enumeration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private Long id;
    private RoleType roleType;
}
