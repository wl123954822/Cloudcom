package com.wl.servicecommodity.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.servicecommodity.common.Result;
import com.wl.servicecommodity.common.ResultEnum;
import com.wl.servicecommodity.entity.Commodity;
import com.wl.servicecommodity.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/commodity")
@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/insertCommodity")
    public JSONObject insertCommodity (Commodity commodity) {
        if (commodityService.insertCommodity(commodity)) {
            return Result.result(ResultEnum.SUCCESS,"success");
        } else {
            return Result.result(ResultEnum.PARAM_ISNULL,"error");
        }
    }

    @RequestMapping("/deleteCommodity")
    public JSONObject deleteCommodity (int itemId) {
        if (commodityService.deleteCommodity(itemId)) {
            return Result.result(ResultEnum.SUCCESS, "success");
        } else {
            return Result.result(ResultEnum.PARAM_ISNULL, "error");
        }
    }

    @RequestMapping("updateCommodity")
    public JSONObject updateCommodity(Commodity commodity) {
        if (commodityService.updateCommodity(commodity)) {
            return Result.result(ResultEnum.SUCCESS, "success");
        } else {
            return Result.result(ResultEnum.PARAM_ISNULL, "error");
        }
    }

    @RequestMapping("/allCommodity")
    public JSONObject listAll(@RequestParam(value = "status", defaultValue = "0") int status) {
        List<Commodity> list = this.commodityService.commodityAll(status);
        return Result.result(ResultEnum.SUCCESS,list,"success");
    }
}
