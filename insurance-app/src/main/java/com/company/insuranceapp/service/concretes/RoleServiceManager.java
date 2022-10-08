package com.company.insuranceapp.service.concretes;

import com.company.insuranceapp.exception.ResourceExistsException;
import com.company.insuranceapp.exception.ResourceNotFoundException;
import com.company.insuranceapp.mapper.RoleMapper;
import com.company.insuranceapp.model.response.RoleResponse;
import com.company.insuranceapp.model.entity.Role;
import com.company.insuranceapp.model.enumeration.RoleType;
import com.company.insuranceapp.model.request.RoleRequest;
import com.company.insuranceapp.repository.RoleRepository;
import com.company.insuranceapp.service.abstracts.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceManager implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        if (doesExistBy(request.getRoleType())) throw new ResourceExistsException("Role", "roleType", request.getRoleType());
        Role role = repository.save(mapper.toEntity(request));
        return mapper.toDto(role);
    }

    @Override
    public Role findByRoleType(RoleType roleType) {
        return repository.findByRoleType(roleType).orElseThrow(() -> new ResourceNotFoundException("Role", "roleType", roleType));
    }

    @Override
    public List<RoleResponse> getRoles() {
        return mapper.toDtoList(repository.findAll());
    }

    private Boolean doesExistBy(RoleType roleType) {
        return repository.existsByRoleType(roleType);
    }

}
