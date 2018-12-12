package com.wl.serviceaddress.common;

import com.alibaba.fastjson.JSONObject;


/**
 * 定义 统一接口返回形式
 * @author wanglei
 */
public class Result {

    /**
     * 调用
     * @param resultEnum
     * @param status
     * @return
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

}
