package com.wl.servicecommodity.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.servicecommodity.common.Result;
import com.wl.servicecommodity.common.ResultEnum;
import com.wl.servicecommodity.entity.Commodity;
import com.wl.servicecommodity.service.CommodityService;
import com.wl.servicecommodity.service.CommoditySurplusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wanglei
 * 商品管理
 */
@RequestMapping("/commodity")
@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;
    @Autowired
    private CommoditySurplusService commoditySurplusService;


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

    /**
     * 显示全部
     * @param status
     * @return
     */
    @RequestMapping("/allCommodity")
    public JSONObject listAll(@RequestParam(value = "status", defaultValue = "0") int status) {
        List<Commodity> list = this.commodityService.commodityAll(status);
        return Result.result(ResultEnum.SUCCESS,list,"success");
    }

    /**
     * 根据id显示
     * @param id
     * @return
     */
    @RequestMapping("/commodityById")
    public JSONObject commodityById(int id) {
        Commodity commodity = this.commodityService.commodById(id);
        return Result.result(ResultEnum.SUCCESS,commodity,"success");
    }

    /**
     * 根据分类id显示
     * @param cid
     * @param status
     * @return
     */
    @RequestMapping("/commodityLisByCid")
    public JSONObject commodityLisByCid(@RequestParam(value = "cid", defaultValue = "0") int cid, @RequestParam(value = "status", defaultValue = "0") int status) {
        List<Commodity> commodities = this.commodityService.commodByCid(cid,status);
        return Result.result(ResultEnum.SUCCESS,commodities,"success");
    }
    /**
     * 增加库存
     * @param itemId
     * @param addNum
     * @return
     */
    @RequestMapping("addItemNum")
    public JSONObject addItemNum(int itemId,int addNum) {
        if (commoditySurplusService.addItemNum(itemId,addNum)) {
            return Result.result(ResultEnum.SUCCESS,"success");
        } else {
            return Result.result(ResultEnum.ERROR,"error");
        }
    }


    /**
     * 减少库存
     *
     * @param itemId
     * @param delNum
     * @return
     */
    @RequestMapping("delItemNum")
    public JSONObject delItemNum(int itemId, int delNum, int oldVersion) {
        if (commoditySurplusService.delItemNum(itemId,delNum,oldVersion)) {
            return Result.result(ResultEnum.SUCCESS, "success");
        } else {
            return Result.result(ResultEnum.ERROR, "error");
        }
    }
}
