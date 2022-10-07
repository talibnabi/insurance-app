package com.company.insuranceapp.repository;


import com.company.insuranceapp.model.entity.Role;
import com.company.insuranceapp.model.enumeration.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByRoleType(RoleType roleType);

    Optional<Role> findByRoleType(RoleType authority);
}
