package com.wl.servicespcart.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.servicespcart.common.Result;
import com.wl.servicespcart.common.ResultEnum;
import com.wl.servicespcart.entity.BuyerItem;
import com.wl.servicespcart.entity.ItemCart;
import com.wl.servicespcart.util.CookieUtil;
import com.wl.servicespcart.util.JsonUtil;
import com.wl.servicespcart.vo.CookieConstant;
import org.omg.CORBA.PUBLIC_MEMBER;
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
import java.util.stream.Collectors;

import static com.wl.servicespcart.vo.CookieConstant.expire;

/**
 * @author wanglei
 * 购物车 controller
 * 1.未登录情况下的购物车，存在cookie中。
 * 2.登录情况下的购物车，存redis中
 */
@RequestMapping("/itemCart")
@RestController
public class ItemCartController {
    private static final Logger logger = LoggerFactory.getLogger(ItemCartController.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/cartAddNoLogin")
    public JSONObject addCart(@RequestParam(value = "itemCart", defaultValue = "null") String itemCartId, BuyerItem buyerItem, HttpServletResponse response, HttpServletRequest request) {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // 1.未登录且未添加过购物车
        if (itemCartId.equals("null")) {
            //设置 购物车 的id-唯一
            String cartId = UUID.randomUUID().toString();
            Integer expire = CookieConstant.expire;
            List<BuyerItem> buyerItems = new ArrayList<>();
            buyerItems.add(buyerItem);
            ItemCart itemCart = new ItemCart(cartId,buyerItems,0,1);
            String itemCartStr = JsonUtil.toJson(itemCart);
            String res = URLEncoder.encode(itemCartStr);
            CookieUtil.set(response, cartId, res, expire);
            logger.info("首次未登录下存购物车信息");
            return Result.result(ResultEnum.SUCCESS, cartId, "success");
        }

        // 2.未登录但是已经添加过购物车
        // 再次添加需要前端 判断 是否添加过，并且将cartId传入
        Cookie itemCartOld = CookieUtil.get(request, itemCartId);
        String res = URLDecoder.decode(itemCartOld.getValue());
        ItemCart itemCart = (ItemCart) JsonUtil.fromJson(res, ItemCart.class);
        // 存放最终的商品
        LinkedHashSet<BuyerItem> newBuyerItemList = new LinkedHashSet<>();
        List<BuyerItem> buyerItemList = itemCart.getBuyerItems();
        for (BuyerItem buyerItem1 : buyerItemList) {
            if (buyerItem1.getItemId() == buyerItem.getItemId()) {
                buyerItem1.setAmount(buyerItem.getAmount()+buyerItem1.getAmount());
            } else {
                newBuyerItemList.add(buyerItem);
            }
        }

        System.out.println(newBuyerItemList.toString());


        buyerItemList.addAll(newBuyerItemList);
        itemCart.setUpdateDate(simpleDateFormat.format(new Date()));
        String itemCartStr = JsonUtil.toJson(itemCart);
        String rests = URLEncoder.encode(itemCartStr);
        // 将结果重新存入cookie
        CookieUtil.set(response, itemCart.getItemCartId(), rests, expire);
        return Result.result(ResultEnum.SUCCESS, rests, "success");
    }

    /**
     * 登录，直接保存在redis中
     *
     * @param userId
     * @param buyerItem
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("cartAddIsLogin")
    public JSONObject cartAddIsLogin(@RequestParam(value = "userId") int userId, BuyerItem buyerItem, HttpServletResponse response, HttpServletRequest request) {
        // 判断有无添加过购物车
        ItemCart itemCart = (ItemCart) redisTemplate.opsForValue().get(userId + "cart");
        if (itemCart == null) {
            String cartId = UUID.randomUUID().toString();
            List<BuyerItem> buyerItems = new ArrayList<>();
            buyerItems.add(buyerItem);
            ItemCart newItemCart = new ItemCart(cartId, buyerItems,userId,1);
            // 将 结果直接存到redis中
            redisTemplate.opsForValue().set(userId + "cart", newItemCart);
            return Result.result(ResultEnum.SUCCESS, cartId, "success");
        }

        // 第n次添加，或删减
        List<BuyerItem> buyerItemList = itemCart.getBuyerItems();
        List<BuyerItem> newItemList = new ArrayList<>();
        for (BuyerItem buyerItem1 : buyerItemList) {
            if (buyerItem.getItemId() == buyerItem1.getItemId()) {
                buyerItem1.setAmount(buyerItem.getAmount() + buyerItem1.getAmount());
                break;
            } else {
                newItemList.add(buyerItem);
            }
        }
        buyerItemList.addAll(newItemList);
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        itemCart.setUpdateDate(sd.format(new Date()));
        redisTemplate.opsForValue().set(userId + "cart", itemCart);
        return Result.result(ResultEnum.SUCCESS, itemCart.getItemCartId(), "success");
    }


    /**
     * 未登录-登录 -将cookie的信息存到redis中,通常在未登录想要提交订单的时候调用
     */
    @RequestMapping("noLoginToLogin")
    public JSONObject noLoginToLogin(@RequestParam(value = "itemCart", defaultValue = "null") String itemCartId,@RequestParam(value = "userId") int userId,
                                     HttpServletResponse response,HttpServletRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // cookie无，redis无
        // 先从cookie中获取 购物车
        Cookie itemCartOld = CookieUtil.get(request, itemCartId);
        if (itemCartOld == null) {
            // 查询是否有redis的购物车
            logger.info("查询redis 获取" + userId + "购物车信息");
            ItemCart itemCartRedis = (ItemCart) redisTemplate.opsForValue().get(userId + "cart");
            // todo,这种情况是不会出现的
            if (itemCartRedis == null) {
                // 无cookie购物车信息-直接创建redis
                String cartId = UUID.randomUUID().toString();
                List<BuyerItem> buyerItems = new ArrayList<>();
                ItemCart newItemCart = new ItemCart(cartId, buyerItems, userId, 1);
                // 将 结果直接存到redis中
                redisTemplate.opsForValue().set(userId + "cart", newItemCart);
                return Result.result(ResultEnum.SUCCESS, "success");
            }
        }
        String res = URLDecoder.decode(itemCartOld.getValue());
        ItemCart itemCart = (ItemCart) JsonUtil.fromJson(res, ItemCart.class);
        ItemCart itemCartRedis = (ItemCart) redisTemplate.opsForValue().get(userId + "cart");
        List<BuyerItem> newBuyerList = new ArrayList<>();
        // redis中无商品信息
        if (itemCartRedis == null) {
            if (itemCart.getBuyerItems().size() !=0) {
                itemCart.setUserId(userId);
                itemCart.setUpdateDate(simpleDateFormat.format(new Date()));
                // 先创建redis
                redisTemplate.opsForValue().set(userId + "cart", itemCart);
            }
            return Result.result(ResultEnum.SUCCESS, "success");
            // redis中有商品信息
        } else {
            for (BuyerItem buyerItemRedis : itemCartRedis.getBuyerItems()) {
                for (BuyerItem buyerItemCookie : itemCart.getBuyerItems()) {
                    if (buyerItemRedis.getItemId() == buyerItemCookie.getItemId()) {
                        buyerItemRedis.setAmount(buyerItemCookie.getAmount()+buyerItemRedis.getAmount());
                    } else {
                        newBuyerList.add(buyerItemCookie);
                    }
                }
            }
            itemCartRedis.getBuyerItems().addAll(newBuyerList);
            redisTemplate.opsForValue().set(userId + "cart", itemCartRedis);
            return Result.result(ResultEnum.SUCCESS, "success");
        }

    }


    /**
     * 未登录情况下，展示购物车信息 cookie
     *
     * @param itemCartId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("showCartNoLogin")
    public JSONObject showCartNoLogin(@RequestParam(value = "itemCart", defaultValue = "null") String itemCartId, HttpServletRequest request, HttpServletResponse response) {

        if (itemCartId.equals("null")) {
            return Result.result(ResultEnum.ERROR, "购物车为空", "error");
        }
        Cookie itemCartOld = CookieUtil.get(request, itemCartId);
        if (itemCartOld != null) {
            String res = URLDecoder.decode(itemCartOld.getValue());
            ItemCart itemCart = (ItemCart) JsonUtil.fromJson(res, ItemCart.class);
            return Result.result(ResultEnum.SUCCESS, itemCart.getBuyerItems(), "success");
        }
        return Result.nullResult();
    }


    @RequestMapping("showCartLogin")
    public JSONObject showCartLogin(@RequestParam(value = "userId") int userId) {
        // 从redis获取购物车
        ItemCart itemCart = (ItemCart) redisTemplate.opsForValue().get(userId + "cart");
        if (itemCart == null) {
            return Result.result(ResultEnum.ERROR, "购物车为空", "error");
        }
        return Result.result(ResultEnum.SUCCESS, itemCart.getBuyerItems(), "success");
    }
 }
