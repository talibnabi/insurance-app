package com.company.insuranceapp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.company.insuranceapp.mapper.UserMapper;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.RegisterRequest;
import com.company.insuranceapp.model.request.UserUpdateRequest;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.model.response.UserResponse;
import com.company.insuranceapp.service.abstracts.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/refresh-token")
    public void refreshToken (HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String prefix = "Bearer ";
        if (authorizationHeader != null && authorizationHeader.startsWith(prefix))
        {
            try
            {
                String refreshToken = authorizationHeader.substring(prefix.length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",
                                user.getRoles().stream().map(role ->
                                        role.getRoleType().name()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception ex)
            {
                ex.printStackTrace();
                response.setHeader("error", "Error");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                List<String> error = Collections.singletonList(ex.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else
            throw new RuntimeException("Refresh token is missing.");
    }

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
