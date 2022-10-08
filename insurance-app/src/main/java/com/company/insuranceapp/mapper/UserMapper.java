package com.company.insuranceapp.mapper;

import com.company.insuranceapp.model.entity.Role;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.RegisterRequest;
import com.company.insuranceapp.model.request.UserUpdateRequest;
import com.company.insuranceapp.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleMapper roleMapper;

    public User toEntity(RegisterRequest request, String password, Set<Role> roles) {
        if (request == null) return null;
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUserName())
                .email(request.getEmail())
                .password(password)
                .roles(roles)
                .build();
    }


    public UserResponse toDto(User entity) {
        if (entity == null) return null;
        return new UserResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPoint(),
                roleMapper.toDtoSet(entity.getRoles()),
                entity.getCourses()
        );
    }

    public void toUser(UserUpdateRequest userUpdateRequest, User user) {
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setPoint(userUpdateRequest.getPoint());
    }
}
