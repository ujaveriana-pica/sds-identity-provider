package com.javeriana.ares.sds.identityprovider.builder;

import com.google.gson.Gson;
import com.javeriana.ares.sds.identityprovider.dataprovider.db.entity.User;
import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.UUID;

public class DataTestBuilder {

    public static final String USER_ID = UUID.randomUUID().toString();
    public static final String USERNAME = "bsilva";
    public static final String PASSWORD = "secret";

    public static UserDetails getUserDetails () {
       return new org.springframework.security.core.userdetails.User
               (USERNAME, PASSWORD, Collections.emptyList());
    }

    public static User getUser() {
        return User.builder()
                .userId(USER_ID)
                .username(USERNAME)
                .password(PASSWORD)
                .name("Bryan")
                .lastName("Silva")
                .email("bsilva@javeriana.edu.co")
                .status("ACTIVE")
                .rol("USER")
                .build();
    }

    public static UserDO getUserDORq() {
        return UserDO.builder()
                .username(USERNAME)
                .password("admin")
                .name("Bryan")
                .lastName("Silva")
                .email("bsilva@javeriana.edu.co")
                .status("ACTIVE")
                .rol("USER")
                .build();
    }

    public static UserDO getUserDORs() {
        UserDO userDO = getUserDORq();
        userDO.setUserId(USER_ID);
        return userDO;
    }

    public static String getUserDORqAsJsonString() {
        return new Gson().toJson(getUserDORq());
    }
}
