package com.company.insuranceapp.controller;

import com.company.insuranceapp.model.dto.UserDTO;
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
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getByUserId(@PathVariable @Min(1) Long userId) {
        return new ResponseEntity<>(userService.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid RegisterRequest request) {
        return new ResponseEntity<>(userService.register(request), CREATED);
    }
}
