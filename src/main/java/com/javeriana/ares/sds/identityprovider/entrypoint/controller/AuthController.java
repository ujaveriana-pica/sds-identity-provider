package com.javeriana.ares.sds.identityprovider.entrypoint.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.Constants;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.ResourceEndpoint;
import com.javeriana.ares.sds.identityprovider.entrypoint.service.UserService;
import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ResourceEndpoint.AUTH_RESOURCE)
@RequiredArgsConstructor
@CrossOrigin(ResourceEndpoint.ALL_ORIGIN)
public class AuthController {

    private final UserService userService;

    @GetMapping(value = ResourceEndpoint.REFRESH_TOKEN_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshToken = request.getHeader(AUTHORIZATION);
        if (Objects.nonNull(refreshToken) && refreshToken.startsWith(Constants.BEARER)) {
            try {
                refreshToken = refreshToken.substring(Constants.BEARER.length());
                Algorithm algorithm = Algorithm.HMAC256(Constants.SECRET.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(refreshToken);
                String username = jwt.getSubject();
                UserDO user = userService.getUserByUsername(username).block();
                String accessToken = JWT.create().withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString()).sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put(Constants.ACCESS_TOKEN, accessToken);
                tokens.put(Constants.REFRESH_TOKEN, refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put(Constants.ERROR_MESSAGE, e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
