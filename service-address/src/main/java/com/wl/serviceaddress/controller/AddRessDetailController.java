package com.wl.serviceaddress.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.serviceaddress.common.Result;
import com.wl.serviceaddress.common.ResultEnum;
import com.wl.serviceaddress.entity.AddRessDetail;
import com.wl.serviceaddress.service.AddressDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("AddRessDetail")
public class AddRessDetailController {

    @Autowired
    private AddressDetailService addressDetailService;


    @RequestMapping("add")
    public JSONObject add(AddRessDetail addRessDetail) {
        if (addressDetailService.insertAddRessDetail(addRessDetail)) {
            return Result.result(ResultEnum.SUCCESS,"success");
        } else {
            return Result.result(ResultEnum.ERROR,"error");
        }
    }
    @RequestMapping("delete")
    public JSONObject delete(int id) {
        if (id == 0) {
            return Result.result(ResultEnum.PARAM_ISNULL,"error");
        } else {
            if (addressDetailService.deleteAddRessDetail(id)) {
                return Result.result(ResultEnum.SUCCESS, "success");
            } else {
                return Result.result(ResultEnum.ERROR, "error");
            }
        }
    }
    @RequestMapping("update")
    public JSONObject upDate (AddRessDetail addRessDetail) {
        if (addressDetailService.updateAddRessDetail(addRessDetail)) {
            return Result.result(ResultEnum.SUCCESS, "success");
        } else {
            return Result.result(ResultEnum.ERROR, "error");
        }
    }
    @RequestMapping("selectByUserId")
    public JSONObject selectByUserId (int userId) {
        if (userId == 0) {
            return Result.result(ResultEnum.PARAM_ISNULL,"error");
        } else {
            List<AddRessDetail> list = this.addressDetailService.listAddressDetail(userId);
            return Result.result(ResultEnum.SUCCESS,list,"success");
        }
    }
    @RequestMapping("selectById")
    public JSONObject sekectById (int id) {
        if (id == 0) {
            return Result.result(ResultEnum.PARAM_ISNULL, "error");
        } else {
            AddRessDetail addRessDetail = this.addressDetailService.selectById(id);
            return Result.result(ResultEnum.SUCCESS, addRessDetail, "success");
        }
    }
    @RequestMapping("changeDefault")
    public JSONObject changeDefault (int id) {
        if (id == 0) {
            return Result.result(ResultEnum.PARAM_ISNULL, "error");
        } else {
            if (this.addressDetailService.changeDefault(id)) {
                return Result.result(ResultEnum.SUCCESS,"success");
            } else {
                return Result.result(ResultEnum.ERROR, "error");
            }
        }
    }
}
