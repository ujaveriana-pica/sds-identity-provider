package com.javeriana.ares.sds.identityprovider.entrypoint.controller;

import com.javeriana.ares.sds.identityprovider.builder.BuilderConfigurationTest;
import com.javeriana.ares.sds.identityprovider.builder.DataTestBuilder;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.Constants;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.ResourceEndpoint;
import com.javeriana.ares.sds.identityprovider.entrypoint.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.core.publisher.Mono;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest extends BuilderConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void refreshTokenIs200() throws Exception {
        Mockito.when(this.userService.getUserByUsername(Mockito.any(String.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.get(
                ResourceEndpoint.AUTH_RESOURCE + ResourceEndpoint.REFRESH_TOKEN_RESOURCE)
                .header(HttpHeaders.AUTHORIZATION, DataTestBuilder.getRefreshToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void refreshTokenIs403() throws Exception {
        Mockito.when(this.userService.getUserByUsername(Mockito.any(String.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.get(
                ResourceEndpoint.AUTH_RESOURCE + ResourceEndpoint.REFRESH_TOKEN_RESOURCE)
                .header(HttpHeaders.AUTHORIZATION, DataTestBuilder.DUE_REFRESH_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void missingRefreshTokenIs403() throws Exception {
        Mockito.when(this.userService.getUserByUsername(Mockito.any(String.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.get(
                ResourceEndpoint.AUTH_RESOURCE + ResourceEndpoint.REFRESH_TOKEN_RESOURCE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void badRefreshTokenIs403() throws Exception {
        Mockito.when(this.userService.getUserByUsername(Mockito.any(String.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.get(
                ResourceEndpoint.AUTH_RESOURCE + ResourceEndpoint.REFRESH_TOKEN_RESOURCE)
                .header(HttpHeaders.AUTHORIZATION, DataTestBuilder.DUE_REFRESH_TOKEN.
                        replace(Constants.BEARER, ""))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
