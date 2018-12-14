package com.wl.adminfeign.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.adminfeign.feign.CommodityFeign;
import com.wl.adminfeign.feign.UserAdminFeign;
import com.wl.entityvo.CommodityClassification;
import com.wl.entityvo.Result;
import com.wl.entityvo.ResultEnum;
import com.wl.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class adminController {

    @Autowired
    private UserAdminFeign userAdminFeign;
    @Autowired
    private CommodityFeign commodityFeign;
    @PostMapping(value = "/menulist")
    public JSONObject login(HttpServletResponse response, HttpServletRequest request)  {
            Cookie cookie = CookieUtil.get(request, "openid");
            JSONObject menuList = userAdminFeign.menuList(cookie.getValue());
            return Result.result(ResultEnum.SUCCESS,menuList,"success");
    }

    @PostMapping(value = "/commodityList")
    public JSONObject commodityList(@RequestParam(value = "cid", defaultValue = "0") int cid, @RequestParam(value = "status", defaultValue = "0") int status) {
        return commodityFeign.commodityLisByCid(cid,status);
    }

    @PostMapping(value = "/commodById")
    public JSONObject commodById(@RequestParam(value = "id") int id) {
        return commodityFeign.commodityById(id);
    }

    @PostMapping(value = "/commodityClassificationList")
    public JSONObject commodityClassificationList(@RequestParam(value = "status", defaultValue = "0") int status) {
        return commodityFeign.selectAllCommondity(status);
    }

    @PostMapping(value = "/addCommodityClassification")
    public JSONObject addCommodityClassification(CommodityClassification commodityClassification) {
        return commodityFeign.addCommondity(commodityClassification);
    }

   @PostMapping(value = "/deleteCommodityClassification")
   public JSONObject deleteCommodityClassification(@RequestParam("commodityId") int commodityId) {
        return commodityFeign.deleteCommondity(commodityId);
   }

   @PostMapping(value = "/updateCommodityClassification")
    public JSONObject updateCommodityClassification(CommodityClassification commodityClassification) {
        return commodityFeign.updateCommondity(commodityClassification);
   }
}
