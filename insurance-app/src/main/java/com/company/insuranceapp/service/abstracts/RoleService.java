package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.dto.RoleDTO;
import com.company.insuranceapp.model.entity.Role;
import com.company.insuranceapp.model.enumeration.RoleType;
import com.company.insuranceapp.model.request.RoleRequest;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleRequest request);

    Role findByRoleType(RoleType authority);

    List<RoleDTO> getRoles();
}
