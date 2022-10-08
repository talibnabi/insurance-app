package com.company.insuranceapp.service.concretes;


import com.company.insuranceapp.mapper.UserMapper;
import com.company.insuranceapp.model.entity.Role;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.RegisterRequest;
import com.company.insuranceapp.model.response.UserResponse;
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
    public UserResponse register(RegisterRequest request) {
        String password = passwordEncoder.encode(request.getPassword());
        Role role = roleService.findByRoleType(request.getRoleType());
        User user = mapper.toEntity(request, password, Set.of(role));
        return mapper.toDto(userRepository.saveAndFlush(user));
    }

    @Override
    public UserResponse getByUserId(Long id) {
        return mapper.toDto(findByUserId(id));
    }

    @Override
    public UserResponse getByUsername(String username) {
        return mapper.toDto(userRepository.findByUsername(username).orElse(null));
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
