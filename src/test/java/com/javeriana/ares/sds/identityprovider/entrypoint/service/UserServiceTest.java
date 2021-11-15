/*
package com.javeriana.ares.sds.identityprovider.entrypoint.service;


import com.javeriana.ares.sds.identityprovider.builder.BuilderConfigurationTest;
import com.javeriana.ares.sds.identityprovider.builder.DataTestBuilder;
import com.javeriana.ares.sds.identityprovider.dataprovider.db.repository.UserRepository;
import com.javeriana.ares.sds.identityprovider.entrypoint.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest extends BuilderConfigurationTest {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.passwordEncoder = Mockito.mock(PasswordEncoder.class);
        this.userService = new UserServiceImpl(this.userRepository, passwordEncoder);
    }

    @Test
    void loadUserByUsernameIsDone() {
        Mockito.when(this.userRepository.getByUsername(Mockito.any(String.class)))
                .thenReturn(Mono.just(DataTestBuilder.getUser()));
        final UserDetails dataResponse = this.userService.loadUserByUsername(DataTestBuilder.USERNAME);
        Assertions.assertNotNull(dataResponse);
    }

}
*/
