package com.example.postit;

public class User {
    private static String username;

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getUsername() {
        if (username == null) {
            setUsername("mihir");
        }
        return username;
    }
}
