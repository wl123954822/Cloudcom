package com.wl.servicespcart.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.servicespcart.common.Result;
import com.wl.servicespcart.common.ResultEnum;
import com.wl.servicespcart.entity.ItemCart;
import com.wl.servicespcart.util.CookieUtil;
import com.wl.servicespcart.util.JsonUtil;
import com.wl.servicespcart.vo.CookieConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wanglei
 * 购物车 controller
 * 1.未登录情况下的购物车，存在cookie中。
 * 2.登录情况下的购物车，存在cookie和redis中
 */
@RequestMapping("/itemCart")
@RestController
public class ItemCartController {
    private static final Logger logger = LoggerFactory.getLogger(ItemCartController.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/cartAddNoLogin")
    public JSONObject addCart(@RequestParam(value = "itemCart",defaultValue = "null") String itemCartId , String[] ids,HttpServletResponse response, HttpServletRequest request) {
        // 1.未登录且未添加过购物车
        if (itemCartId.equals("null")) {
            //设置 购物车 的id-唯一
            String cartId = UUID.randomUUID().toString();
            Integer expire = CookieConstant.expire;
            ItemCart itemCart = new ItemCart();
            itemCart.setItemCartId(cartId);
            itemCart.setItemIds(Arrays.asList(ids));
            SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
            itemCart.setCreateDate(sd.format(new Date()));
            itemCart.setUserId(0);
            String itemCartStr = JsonUtil.toJson(itemCart);
            String res = URLEncoder.encode(itemCartStr);
            CookieUtil.set(response, cartId, res, expire);
            logger.info("首次未登录下存购物车信息");
            return Result.result(ResultEnum.SUCCESS,cartId,"success");
        }

        // 2.未登录但是已经添加过购物车
        // 再次添加需要前端 判断 是否添加过，并且将cartId传入
        Cookie itemCartOld = CookieUtil.get(request,itemCartId);
        String res = URLDecoder.decode(itemCartOld.getValue());
        ItemCart itemCart = (ItemCart) JsonUtil.fromJson(res,ItemCart.class);
        List<String> oldIds = itemCart.getItemIds();
        List<String> newIds = Arrays.asList(ids);
        ArrayList<String> arrNewIds = new ArrayList<>(newIds);
        arrNewIds.removeAll(oldIds);
        oldIds.addAll(arrNewIds);
        // 重新对新增的赋值
        itemCart.setItemIds(oldIds);
        String itemCartStr = JsonUtil.toJson(itemCart);
        String rests = URLEncoder.encode(itemCartStr);
        return Result.result(ResultEnum.SUCCESS, rests, "success");
    }

    /**
     * 登录，直接保存在redis中
     * @param userId
     * @param ids
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("cartAddIsLogin")
    public JSONObject cartAddIsLogin(@RequestParam(value = "userId") int userId, String[] ids, HttpServletResponse response, HttpServletRequest request) {
        // 判断有无添加过购物车
        ItemCart itemCart= (ItemCart) redisTemplate.opsForValue().get(userId+"cart");
        if (itemCart == null) {
            String cartId = UUID.randomUUID().toString();
            ItemCart newItemCart = new ItemCart();
            newItemCart.setItemIds(Arrays.asList(ids));
            newItemCart.setUserId(userId);
            newItemCart.setItemCartId(cartId);
            SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
            itemCart.setCreateDate(sd.format(new Date()));
            // 将 结果直接存到redis中
            redisTemplate.opsForValue().set(userId+"cart",itemCart);
            return Result.result(ResultEnum.SUCCESS, cartId,"success");
        }

        // 第n次添加，或删减
        List<String> oldIds = itemCart.getItemIds();
        List<String> newIds = Arrays.asList(ids);
        ArrayList<String> arrNewIds = new ArrayList<>(newIds);
        arrNewIds.removeAll(oldIds);
        oldIds.addAll(arrNewIds);
        // 重新对新增的赋值
        itemCart.setItemIds(oldIds);
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        itemCart.setCreateDate(sd.format(new Date()));
        redisTemplate.opsForValue().set(userId+"cart",itemCart);
        return Result.result(ResultEnum.SUCCESS, itemCart.getItemCartId(), "success");
    }
}
