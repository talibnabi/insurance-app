package com.company.insuranceapp.controller;

import com.company.insuranceapp.model.dto.RoleDTO;
import com.company.insuranceapp.service.abstracts.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/roles")
public class RoleController {
    private final RoleService roleService;
   
}
