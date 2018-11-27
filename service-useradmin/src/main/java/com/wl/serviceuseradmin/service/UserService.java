package com.wl.serviceuseradmin.service;

import com.wl.serviceuseradmin.entity.User;
import java.util.List;



public interface UserService {

    public User loadUserByUsername(String s);

    public int UserReg(String username, String password);

    public List<User> getAllUser();
}
