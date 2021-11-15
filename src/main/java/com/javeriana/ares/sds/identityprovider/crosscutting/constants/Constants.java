package com.javeriana.ares.sds.identityprovider.crosscutting.constants;

public class Constants {

    private Constants() {
    }

    public static final String USER_ID = "user-id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SECRET = "secret";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final Long ACCESS_TOKEN_TIME = System.currentTimeMillis() + 30 * 60 * 1000;
    public static final Long REFRESH_TOKEN_TIME = System.currentTimeMillis() + 60 * 60 * 1000;
    public static final String ERROR_MESSAGE = "error_message";
    public static final String BEARER = "Bearer ";

}
