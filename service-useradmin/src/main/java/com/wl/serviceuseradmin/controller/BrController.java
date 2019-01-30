package com.wl.serviceuseradmin.controller;



import com.wl.serviceuseradmin.common.UserUtiles;
import com.wl.serviceuseradmin.entity.Menu;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class BrController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/br")
    public String br (HttpServletResponse response, HttpServletRequest request) {
        return "br";
    }

    @GetMapping("/br2")
    public String br2(HttpServletRequest request,HttpServletResponse response) {
        User user = UserUtiles.getCurrentUser();
        List<Menu> menus = menuService.getMenusByUserId();
        System.out.println(user);
        request.setAttribute("menusLis",menus);
        request.setAttribute("users",user);

        return "/news/index";
    }

    @GetMapping("/br3")
    public String  br3(HttpServletResponse response ,HttpServletRequest request) {
        return "news/userDetail";
    }


}
