package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.response.UserResponse;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.RegisterRequest;

public interface UserService {
    UserResponse register(RegisterRequest request);

    UserResponse getByUserId(Long id);

    UserResponse getByUsername (String username);

    User findByUserId(Long id);
}
