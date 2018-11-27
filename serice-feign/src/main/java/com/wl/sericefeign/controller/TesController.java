package com.wl.sericefeign.controller;import com.alibaba.fastjson.JSONObject;import com.wl.sericefeign.feign.SchedualServiceTes;import com.wl.sericefeign.feign.UserAdminFeign;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;@RestControllerpublic class TesController  {    @Autowired    private SchedualServiceTes schedualServiceTes;    @Autowired    private UserAdminFeign userAdminFeign;    @GetMapping(value = "/hi")    public String tes(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("token") String token) {       Object aa =  userAdminFeign.login(username, password,token);        System.out.println("why"+aa.toString());        Object o = userAdminFeign.getUser(token);        System.out.println("ou no "+o.toString());        return schedualServiceTes.tesClientOne();    }    @PostMapping(value = "/user")    public JSONObject getUser(@RequestParam("token") String token) {        return userAdminFeign.getUser(token);    }    @PostMapping(value = "/login")    public JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("token") String token){        return userAdminFeign.login(username,password,token);    }}