package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.dto.UserDTO;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.RegisterRequest;

public interface UserService {
    UserDTO register(RegisterRequest request);

    UserDTO getByUserId(Long id);

    User findByUserId(Long id);
}
