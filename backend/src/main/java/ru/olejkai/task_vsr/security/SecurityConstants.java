package ru.olejkai.task_vsr.security;

public class SecurityConstants {
//    public static final String SIGN_UP_URLS = "/start/**";
    public static final String SIGN_UP_URLS = "/start/signin";

    public static final String SECRET = "SecretKeyGenJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TIME = 600_000; //10min
}
