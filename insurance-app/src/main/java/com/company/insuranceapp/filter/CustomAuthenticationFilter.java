package com.company.insuranceapp.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

@RequiredArgsConstructor
public class CustomAuthenticationFilter
{
    private final AuthenticationManager authenticationManager;

//    @Override
//    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException
//    {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        System.out.println(username);
//        System.out.println(password);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        return authenticationManager.authenticate(token);
//    }

//    @Override
//    protected void successfulAuthentication (HttpServletRequest request, HttpServletResponse response,
//                                             FilterChain chain, Authentication authentication) throws IOException
//    {
//        User user = (User) authentication.getPrincipal();
//        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//        String access_token = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
//                .withIssuer(request.getRequestURL().toString())
//                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
//                        Collectors.toList()))
//                .sign(algorithm);
//
//        String refresh_token = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 31))
//                .withIssuer(request.getRequestURL().toString())
//                .sign(algorithm);
//
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("access_token", access_token);
//        tokens.put("refresh_token", refresh_token);
//        response.setContentType(APPLICATION_JSON_VALUE);
//        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//
//    }
}