package com.company.insuranceapp.service.concretes;


import com.company.insuranceapp.exception.ResourceNotFoundException;
import com.company.insuranceapp.mapper.UserMapper;
import com.company.insuranceapp.model.dto.UserDTO;
import com.company.insuranceapp.model.entity.Role;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.RegisterRequest;
import com.company.insuranceapp.repository.UserRepository;
import com.company.insuranceapp.service.abstracts.RoleService;
import com.company.insuranceapp.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceManager implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserDTO register(RegisterRequest request) {
        String password = passwordEncoder.encode(request.getPassword());
        Role role = roleService.findByRoleType(request.getRoleType());
        User user = mapper.toEntity(request, password, Set.of(role));
        return mapper.toDto(userRepository.saveAndFlush(user));
    }

    @Override
    public UserDTO getByUserId(Long id) {
        return mapper.toDto(findByUserId(id));
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
