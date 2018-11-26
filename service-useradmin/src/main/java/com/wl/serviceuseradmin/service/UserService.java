package com.wl.serviceuseradmin.service;


import com.wl.serviceuseradmin.dao.UserDao;
import com.wl.serviceuseradmin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

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

    public int UserReg(String username, String password) {

        // 对参数非空校验
        if ("".equals(username) || username == null) {
            return  -2;
        }
        if ("".equals(password) || password == null) {
            return  -2;
        }
        //如果用户名存在，返回错误
        if (userDao.loadUserByUserName(username) != null) {
            return  -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        return userDao.userReg(username, password);
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
