package com.wl.adminfeign.feign;

import com.alibaba.fastjson.JSONObject;
import com.wl.entityvo.CommodityClassification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-commodity")
public interface CommodityFeign {
    /**
     * 显示全部 商品
     * @param cid
     * @param status
     * @return
     */
    @RequestMapping(value = "/commodity/commodityLisByCid", method = RequestMethod.POST)
    JSONObject commodityLisByCid(@RequestParam("cid") int cid, @RequestParam("status") int status);

    /**
     * 显示全部商品分类
     */
    @RequestMapping(value = "/commondityClas/selectAllCommondity",method = RequestMethod.POST)
    JSONObject selectAllCommondity(@RequestParam("status") int status);

    @RequestMapping(value = "/commondityClas/addCommondity" ,method = RequestMethod.POST)
    JSONObject addCommondity(CommodityClassification commodityClassification);

    @RequestMapping(value = "/commondityClas/deleCommondity", method = RequestMethod.POST)
    JSONObject deleteCommondity(@RequestParam("commodityId") int commodityId);

    @RequestMapping(value = "/commondityClas/updateCommondity", method = RequestMethod.POST)
    JSONObject updateCommondity(CommodityClassification commodityClassification);


}
