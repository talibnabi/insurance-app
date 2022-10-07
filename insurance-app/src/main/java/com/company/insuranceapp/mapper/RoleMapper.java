package com.company.insuranceapp.mapper;

import com.company.insuranceapp.model.dto.RoleDTO;
import com.company.insuranceapp.model.entity.Role;
import com.company.insuranceapp.model.request.RoleRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    public RoleDTO toDto(Role entity) {
        if (entity == null) return null;
        return new RoleDTO(entity.getId(), entity.getRoleType());
    }

    public Set<RoleDTO> toDtoSet(Set<Role> entitySet) {
        if (entitySet == null) return Collections.emptySet();
        return entitySet.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public List<RoleDTO> toDtoList(List<Role> entityList) {
        if (entityList == null) return Collections.emptyList();
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Role toEntity(RoleRequest request) {
        if (request == null) return null;
        return Role.builder().roleType(request.getRoleType()).build();
    }
}
