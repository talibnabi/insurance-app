package com.company.insuranceapp.security;


import com.company.insuranceapp.model.request.LoginRequest;
import com.company.insuranceapp.model.response.JWTResponse;
import com.company.insuranceapp.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    public JWTResponse createJwtToken(LoginRequest request) {
        String username = request.getUsername();
        String userPassword = request.getPassword();
        authenticate(username, userPassword);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String newGeneratedToken = tokenProvider.generateToken(userDetails);
        return new JWTResponse(newGeneratedToken);
    }

    private void authenticate(String userName, String userPassword) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
