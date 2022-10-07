package com.company.insuranceapp.model.dto;

import com.company.insuranceapp.model.enumeration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private RoleType roleType;
}
