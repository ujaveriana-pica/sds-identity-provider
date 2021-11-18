package com.javeriana.ares.sds.identityprovider.builder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.Constants;
import com.javeriana.ares.sds.identityprovider.dataprovider.db.entity.User;
import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

public class DataTestBuilder {

    public static final String DUE_REFRESH_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJic2lsdmEiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjkwMzAvYXBpL2F1dGgvYWNjZXNzLXRva2VuIiwiZXhwIjoxNjM3MTk3NDg3LCJyb2wiOiJBRE1JTiJ9.GqmyOLI-mcb6g1H1s3cKj8V9cUTfrTGoziTjm1wVh6U";
    public static final String USER_ID = UUID.randomUUID().toString();
    public static final String USERNAME = "bsilva";
    public static final String ROL = "USER";
    public static final String PASSWORD = "secret";

    public static String getRefreshToken(){
        Algorithm algorithm = Algorithm.HMAC256(Constants.SECRET.getBytes());
        return "Bearer " + JWT.create().withSubject(USERNAME)
                .withClaim(Constants.ROL, ROL)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer("test").sign(algorithm);
    }

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
