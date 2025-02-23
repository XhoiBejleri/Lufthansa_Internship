package org.example.util;


public final class Queries {

    public static final String FIND_ALL_USERS = "SELECT u FROM User u";
    public static final String FIND_USER_BY_EMAIL = "SELECT u FROM User u JOIN u.userDetails ud WHERE ud.email = ?1";
    public static final String FIND_USER_BY_EMAIL_METHOD_2 = "SELECT u FROM User u JOIN u.userDetails ud WHERE ud.email = :email";

    private Queries() {}
}
