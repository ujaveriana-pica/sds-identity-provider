package com.javeriana.ares.sds.identityprovider.crosscutting.constants;

public class ResourceEndpoint {

    private ResourceEndpoint() {
    }

    public static final String ALL_ORIGIN = "*";
    public static final String API = "/api";
    public static final String USER_RESOURCE = API + "/user";
    public static final String AUTH_RESOURCE = API + "/auth";
    public static final String SIGN_UP_RESOURCE = "/sign-up";
    public static final String UPDATE_USER_RESOURCE = "/{user-id}/update";
    public static final String GET_USER_RESOURCE = "/{username}/detail";
    public static final String REFRESH_TOKEN_RESOURCE = "/refresh-token";
    public static final String ACCESS_TOKEN_AUTH_RESOURCE = AUTH_RESOURCE + "/access-token";
    public static final String SIGN_UP_AUTH_RESOURCE = USER_RESOURCE + SIGN_UP_RESOURCE;
}
