package com.wl.serviceuseradmin.service;

import com.wl.serviceuseradmin.entity.User;
import java.util.List;



public interface UserService {

     User loadUserByUsername(String s);

     int UserReg(String username, String password);

     List<User> getAllUser();

    User loadUserByOpenid(String openId);
}
