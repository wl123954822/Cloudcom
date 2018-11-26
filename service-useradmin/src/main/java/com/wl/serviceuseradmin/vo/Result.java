package com.wl.serviceuseradmin.vo;

import com.alibaba.fastjson.JSONObject;
import com.wl.serviceuseradmin.enu.ResultEnum;


/**
 * 定义 统一接口返回形式
 */
public class Result {

    /**
     * 成功时返回
     */
    public static JSONObject result (ResultEnum resultEnum, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",resultEnum.getCode());
        jsonObject.put("msg",resultEnum.getMessage());
        jsonObject.put("status",status);
        JSONObject dataJson = new JSONObject();
        dataJson.put("result",jsonObject);
        return dataJson;
    }

    public static JSONObject result(ResultEnum resultEnum, Object data, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", resultEnum.getCode());
        jsonObject.put("msg", resultEnum.getMessage());
        jsonObject.put("data",data);
        jsonObject.put("status", status);
        JSONObject dataJson = new JSONObject();
        dataJson.put("result", jsonObject);
        return dataJson;
    }

    public static JSONObject nullResult() {
        return new JSONObject();
    }

    /**
     * 新增 --统一返回
     */
    public static JSONObject addResult(int result) {
        if (result == -2) {
            return Result.result(ResultEnum.PARAM_ISNULL, "error");
        } else if (result == -1) {
            return Result.result(ResultEnum.RESULT_REPEAT, "error");
        } else {
            return Result.result(ResultEnum.SUCCESS, "success");
        }
    }
}
