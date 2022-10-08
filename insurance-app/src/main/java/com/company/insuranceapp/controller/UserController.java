package com.company.insuranceapp.controller;

import com.company.insuranceapp.mapper.UserMapper;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.UserUpdateRequest;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.model.response.UserResponse;
import com.company.insuranceapp.model.request.RegisterRequest;
import com.company.insuranceapp.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static org.springframework.http.HttpStatus.CREATED;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getByUserId(@PathVariable @Min(1) Long userId) {
        return new ResponseEntity<>(userService.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody @Valid RegisterRequest request) {
        return new ResponseEntity<>(userService.register(request), CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseModel<UserResponse>> update (@RequestBody UserUpdateRequest userUpdateRequest)
    {
        User user = userService.findByUserId(userUpdateRequest.getId());
        if (user == null)
            return ResponseEntity.ok(ResponseModel.notFound("User"));

        userMapper.toUser(userUpdateRequest, user);
        user = userService.update(user);
        return ResponseEntity.ok(ResponseModel.success(userMapper.toDto(user)));
    }
}
