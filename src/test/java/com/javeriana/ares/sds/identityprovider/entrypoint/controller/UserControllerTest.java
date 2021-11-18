package com.javeriana.ares.sds.identityprovider.entrypoint.controller;

import com.javeriana.ares.sds.identityprovider.builder.BuilderConfigurationTest;
import com.javeriana.ares.sds.identityprovider.builder.DataTestBuilder;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.ResourceEndpoint;
import com.javeriana.ares.sds.identityprovider.entrypoint.service.UserService;
import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.core.publisher.Mono;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest extends BuilderConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void saveUserIs200() throws Exception {
        Mockito.when(this.userService.saveUser(Mockito.any(UserDO.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.post(
                ResourceEndpoint.USER_RESOURCE + ResourceEndpoint.SIGN_UP_RESOURCE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(DataTestBuilder.getUserDORqAsJsonString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateUserUserIs200() throws Exception {
        Mockito.when(this.userService.updateUser(Mockito.any(UserDO.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.patch(
                ResourceEndpoint.USER_RESOURCE + ResourceEndpoint.UPDATE_USER_RESOURCE,
                DataTestBuilder.USER_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(DataTestBuilder.getUserDORqAsJsonString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getUserByUsernameIs200() throws Exception {
        Mockito.when(this.userService.getUserByUsername(Mockito.any(String.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUserDORs()));
        this.mockMvc.perform(MockMvcRequestBuilders.get(
                ResourceEndpoint.USER_RESOURCE + ResourceEndpoint.GET_USER_RESOURCE,
                DataTestBuilder.USERNAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
