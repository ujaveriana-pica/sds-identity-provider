package com.javeriana.ares.sds.identityprovider.crosscutting.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.Constants;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.ResourceEndpoint;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getServletPath().equals(ResourceEndpoint.SIGN_UP_AUTH_RESOURCE)) {
            if (request.getServletPath().startsWith(ResourceEndpoint.USER_RESOURCE)) {
                String xAuthToken = request.getHeader(Constants.X_AUTH_TOKEN);
                try {
                    String[] credentials = new String(Base64.decode(xAuthToken)).split(";");
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            credentials[0], null, Collections.singletonList(new SimpleGrantedAuthority(credentials[1])));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } catch (Exception e) {
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put(Constants.ERROR_MESSAGE, e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
