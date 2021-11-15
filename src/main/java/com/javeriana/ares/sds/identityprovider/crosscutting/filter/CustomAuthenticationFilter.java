package com.javeriana.ares.sds.identityprovider.crosscutting.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.Constants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            Map<String, String> credentials = new ObjectMapper().readValue(
                    CharStreams.toString(request.getReader()), Map.class);
            String username = credentials.get(Constants.USERNAME);
            String password = credentials.get(Constants.PASSWORD);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(Constants.SECRET.getBytes());
        String accessToken = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(Constants.ACCESS_TOKEN_TIME))
                .withIssuer(request.getRequestURL().toString()).sign(algorithm);
        String refreshToken = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(Constants.REFRESH_TOKEN_TIME))
                .withIssuer(request.getRequestURL().toString()).sign(algorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put(Constants.ACCESS_TOKEN, accessToken);
        tokens.put(Constants.REFRESH_TOKEN, refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
