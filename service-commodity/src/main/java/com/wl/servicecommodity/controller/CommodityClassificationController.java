package com.wl.servicecommodity.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.servicecommodity.common.Result;
import com.wl.servicecommodity.common.ResultEnum;
import com.wl.servicecommodity.entity.CommodityClassification;
import com.wl.servicecommodity.service.CommodityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglei
 */
@RestController
@RequestMapping("/commondityClas")
public class CommodityClassificationController {

    @Autowired
    private CommodityClassificationService commodityClassificationService;


    @RequestMapping("/addCommondity")
    public JSONObject addCommondity (CommodityClassification commodityClassification) {
        if (commodityClassificationService.insertCommodityClassification(commodityClassification)) {
            return Result.result(ResultEnum.SUCCESS,"success");
        } else {
            return Result.result(ResultEnum.RESULT_REPEAT,"error");
        }
    }

    @RequestMapping("/deleCommondity")
    public JSONObject deleCommondity(int commodityId) {
        if (commodityClassificationService.deleteCommodityClassification(commodityId)) {
            return Result.result(ResultEnum.SUCCESS,"success");
        } else {
            return Result.result(ResultEnum.RESULT_REPEAT, "error");
        }
    }

    @RequestMapping("/updateCommondity")
    public JSONObject updateCommondity (CommodityClassification commodityClassification) {
        if (commodityClassificationService.updateCommodityClassification(commodityClassification)) {
            return Result.result(ResultEnum.SUCCESS,"success");
        } else {
            return Result.result(ResultEnum.PARAM_ISNULL,"error");
        }
    }

    @RequestMapping("/selectAllCommondity")
    public JSONObject selectAllCommondity(@RequestParam(value = "status", defaultValue = "0" ) int status) {
        List<CommodityClassification> commodityClassification = this.commodityClassificationService.selectAllCommod(status);
        return Result.result(ResultEnum.SUCCESS,commodityClassification,"success");
    }

    @RequestMapping("/selectCommondityById")
    public JSONObject selectCommondityById(Integer id) {
        CommodityClassification commodityClassification = this.commodityClassificationService.selectByCommonId(id);
        return Result.result(ResultEnum.SUCCESS,commodityClassification,"success");
    }

    @RequestMapping("/toIdList")
    public List<Integer> selectCommondityByIdList(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        return ids;
    }

    @RequestMapping("/selectCommondityByIdList")
    public JSONObject selectByIds(List<Integer> ids, @RequestParam(value = "status", defaultValue = "0") int status) {
        List<CommodityClassification> list = this.commodityClassificationService.selectByCommonIdList(ids,status);
        return Result.result(ResultEnum.SUCCESS,list,"success");
    }
}
