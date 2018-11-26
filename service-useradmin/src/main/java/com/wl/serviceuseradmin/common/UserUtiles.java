package com.wl.serviceuseradmin.common;


import com.wl.serviceuseradmin.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtiles {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
