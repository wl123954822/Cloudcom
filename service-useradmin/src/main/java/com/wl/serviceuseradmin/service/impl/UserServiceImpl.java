/*
package com.wl.serviceuseradmin.service.impl;

import com.wl.serviceuseradmin.dao.UserDao;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.loadUserByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return user;
    }

    @Override
    public int UserReg(String username, String password) {

        // 对参数非空校验
        if ("".equals(username) || username == null) {
            return -2;
        }
        if ("".equals(password) || password == null) {
            return -2;
        }
        //如果用户名存在，返回错误
        if (userDao.loadUserByUserName(username) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        return userDao.userReg(username, password);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User loadUserByOpenid(String openId) {
        User user = new User();
        if (!StringUtils.isEmpty(openId)) {
            user = this.userDao.getUserByOpenId(openId);
        }
        return user;
    }
}
*/
