package org.example.sweater.domain.util;


import org.example.sweater.domain.User;

public abstract class MessageHelper {

    public static String getAuthorName(User user) {
        return user != null ? user.getUsername() : "neizvestna";
    }
}
