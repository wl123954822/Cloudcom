package com.wl.serviceuseradmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StaticController {

    @RequestMapping("/tologin")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }


}
