/*
package com.javeriana.ares.sds.identityprovider.entrypoint.controller;

import com.javeriana.ares.sds.identityprovider.configuration.security.SecurityConfiguration;
import com.javeriana.ares.sds.identityprovider.entrypoint.service.UserService;
import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import com.javeriana.ares.sds.identityprovider.builder.BuilderConfigurationTest;
import com.javeriana.ares.sds.identityprovider.builder.DataTestBuilder;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.ResourceEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import reactor.core.publisher.Mono;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest extends BuilderConfigurationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
        Mockito.when(this.userDetailsService.loadUserByUsername(Mockito.anyString()))
                .thenReturn(DataTestBuilder.getUserDetails());
    }

    @Test
    void saveUserIs200() throws Exception {
        Mockito.when(this.userService.saveUser(Mockito.any(UserDO.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.post(ResourceEndpoint.SIGN_UP_RESOURCE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(DataTestBuilder.getUserDORqAsJsonString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateUserUserIs200() throws Exception {
        Mockito.when(this.userService.updateUser(Mockito.any(UserDO.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(
                this.mockMvc.perform(MockMvcRequestBuilders.patch(ResourceEndpoint.UPDATE_USER_RESOURCE,
                        DataTestBuilder.USER_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DataTestBuilder.getUserDORqAsJsonString()))
                        .andExpect(MockMvcResultMatchers.request().asyncStarted())
                        .andReturn()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getUserByUsernameIs200() throws Exception {
        Mockito.when(this.userService.getUserByUsername(Mockito.any(String.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(
                this.mockMvc.perform(MockMvcRequestBuilders.get(ResourceEndpoint.GET_USER_RESOURCE,
                        DataTestBuilder.USERNAME)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.request().asyncStarted())
                        .andReturn()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
*/
