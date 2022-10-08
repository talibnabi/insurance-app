package com.company.insuranceapp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.company.insuranceapp.mapper.UserMapper;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.request.LoginRequest;
import com.company.insuranceapp.model.request.RegisterRequest;
import com.company.insuranceapp.model.request.UserUpdateRequest;
import com.company.insuranceapp.model.response.JWTResponse;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.model.response.UserResponse;
import com.company.insuranceapp.service.abstracts.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/getUser")
    public ResponseEntity<ResponseModel<UserResponse>> getUser(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String prefix = "Bearer ";
        String token = authorizationHeader.substring(prefix.length());
        User user = getUserFromToken(token);
        return ResponseEntity.ok(ResponseModel.success(userMapper.toDto(user)));
    }

    private User getUserFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return userService.getByUsername(username);
    }

    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String prefix = "Bearer ";
        if (authorizationHeader != null && authorizationHeader.startsWith(prefix)) {
            try {
                String refreshToken = authorizationHeader.substring(prefix.length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                User user = getUserFromToken(refreshToken);
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
            } catch (Exception ex) {
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
    public ResponseEntity<ResponseModel<UserResponse>> getByUserId(@PathVariable @Min(1) Long userId) {
        User user = userService.findByUserId(userId);
        if (user == null)
            ResponseEntity.ok(ResponseModel.notFound("User"));

        return ResponseEntity.ok(ResponseModel.success(userMapper.toDto(user)));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseModel<UserResponse>> signUp(@RequestBody @Valid RegisterRequest request) {
        UserResponse userResponse = userService.register(request);

        return ResponseEntity.ok(ResponseModel.success(userResponse));
    }

    @PutMapping
    public ResponseEntity<ResponseModel<UserResponse>> update(@RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userService.findByUserId(userUpdateRequest.getId());
        if (user == null)
            return ResponseEntity.ok(ResponseModel.notFound("User"));

        userMapper.toUser(userUpdateRequest, user);
        user = userService.update(user);
        return ResponseEntity.ok(ResponseModel.success(userMapper.toDto(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel<JWTResponse>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        System.out.println(loginRequest);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword());
        authenticationManager.authenticate(token);

        User user = userService.getByUsername(loginRequest.getUsername());

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(loginRequest.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getRoles().stream().map(role -> role.getRoleType().name()).collect(
                        Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(request.getRequestURL().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 31))
                .sign(algorithm);

        JWTResponse jwtResponse = new JWTResponse(access_token, refresh_token);
        return ResponseEntity.ok(ResponseModel.success(jwtResponse));
    }
}
