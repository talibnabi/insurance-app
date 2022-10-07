package com.company.insuranceapp.service.concretes;


import com.company.insuranceapp.model.dto.UserDTO;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.RegisterRequest;
import com.company.insuranceapp.service.abstracts.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceManager implements UserService {

    @Override
    public UserDTO register(RegisterRequest request) {
        return null;
    }

    @Override
    public UserDTO getByUserId(Long id) {
        return null;
    }

    @Override
    public User findByUserId(Long id) {
        return null;
    }
}
