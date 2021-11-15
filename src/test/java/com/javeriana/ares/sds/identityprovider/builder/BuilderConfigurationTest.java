package com.javeriana.ares.sds.identityprovider.builder;

import com.javeriana.ares.sds.identityprovider.ApplicationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(classes = {ApplicationTest.class})
@TestPropertySource(properties = {"SPRING_PROFILES_ACTIVE=test"})
public abstract class BuilderConfigurationTest {
}
