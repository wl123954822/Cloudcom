package com.wl.serviceuseradmin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体
 */
public class User implements UserDetails {
        private Long id;
        private String name;
        private String phone;
        private String telephone;
        private String address;
        private boolean enabled;
        private String username;
        private String password;
        private String remark;
        private List<Role> roles;
        private String userface;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        public String getUserface() {
            return userface;
        }

        public void setUserface(String userface) {
            this.userface = userface;
        }

        @JsonIgnore
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return authorities;
        }

        @JsonIgnore
        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @JsonIgnore
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @JsonIgnore
        @Override
        public boolean isAccountNonLocked() { // 是否被锁定
            return true;
        }

        @JsonIgnore
        @Override
        public boolean isCredentialsNonExpired() { //用户凭证是否过期
            return true;
        }

        @Override
        public boolean isEnabled() {// 是否激活
            return enabled;
        }

        //JsonIgnore 的作用是返回前台不带该参数


        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", address='" + address + '\'' +
                    ", enabled=" + enabled +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", remark='" + remark + '\'' +
                    ", roles=" + roles +
                    ", userface='" + userface + '\'' +
                    '}';
        }

    }
