package com.project.se2project.constant;

public class SecurityConstant {

    public static final String ADMIN_NAME = "admin";
    public static final String ADMIN_PASSWORD = "admin";
    public static final String ADMIN_DOB = "15/01/1992";
    public static final long EXPIRATION_TIME = 432_000_000; //5 days
    public static final String TOKEN_PREFIX = "Bear ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

    public static final Integer COOKIE_EXPIRIED = 86400 * 30;
    public static final long JWT_EXPIRIED = 60L * 60 * 24 * 30 * 1000;
    public static final String[] PUBLIC_URLS = {"/user/login", "/user/register", "user/resetpassword/**", "/user/image/**"};
//    public static final String[] PUBLIC_URLS = {"**"};
}
