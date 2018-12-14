package com.wl.servicespcart.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.servicespcart.common.Result;
import com.wl.servicespcart.common.ResultEnum;
import com.wl.servicespcart.entity.BuyerItem;
import com.wl.servicespcart.entity.ItemCart;
import com.wl.servicespcart.service.ItemCartService;
import com.wl.servicespcart.util.CookieUtil;
import com.wl.servicespcart.util.JsonUtil;
import com.wl.servicespcart.vo.CookieConstant;
import com.wl.servicespcart.vo.ItemCartVo;
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
 * 1.未登录情况下的购物车，只存redis中。
 * 2.登录情况下的购物车，存redis中，并且入库
 */
@RequestMapping("/itemCart")
@RestController
public class ItemCartController {
    private static final Logger logger = LoggerFactory.getLogger(ItemCartController.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ItemCartService itemCartService;

    @RequestMapping("/cartAddNoLogin")
    public JSONObject addCart(@RequestParam(value = "itemCart", defaultValue = "null") String itemCartId, BuyerItem buyerItem, HttpServletResponse response, HttpServletRequest request) {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // 1.未登录且未添加过购物车
        if ("null".equals(itemCartId)) {
            //设置 购物车 的id-唯一
            String cartId = UUID.randomUUID().toString();
            Integer expire = CookieConstant.expire;
            List<BuyerItem> buyerItems = new ArrayList<>();
            buyerItems.add(buyerItem);
            ItemCart itemCart = new ItemCart(cartId,buyerItems,0,1);
            String itemCartStr = JsonUtil.toJson(itemCart);
            redisTemplate.opsForValue().set(cartId,itemCartStr);
            logger.info("首次未登录下存购物车信息");
            return Result.result(ResultEnum.SUCCESS, cartId, "success");
        }

        // 2.未登录但是已经添加过购物车
        // 再次添加需要前端 判断 是否添加过，并且将cartId传入
        String itemCartStrOld = (String) redisTemplate.opsForValue().get(itemCartId);
        ItemCart itemCart = (ItemCart) JsonUtil.fromJson(itemCartStrOld, ItemCart.class);
        // 存放最终的商品
        LinkedHashSet<BuyerItem> newBuyerItemList = new LinkedHashSet<>();
        List<BuyerItem> buyerItemList = itemCart.getBuyerItems();
        for (BuyerItem buyerItem1 : buyerItemList) {
            if (buyerItem1.getItemId() == buyerItem.getItemId()) {
                buyerItem1.setAmount(buyerItem.getAmount()+buyerItem1.getAmount());
                newBuyerItemList.add(buyerItem1);
            } else {
                newBuyerItemList.add(buyerItem);
            }
        }
        if ( newBuyerItemList.size() >1 ) {
           //Optional<BuyerItem> bu = newBuyerItemList.stream().collect(Collectors.maxBy(Comparator.comparing(BuyerItem::getAmount)));
           // System.out.println(bu.toString());
           //buyerItemList.addAll();
        } else {
            buyerItemList.addAll(newBuyerItemList);
        }

        itemCart.setUpdateDate(simpleDateFormat.format(new Date()));
        String itemCartStr = JsonUtil.toJson(itemCart);
        // 将结果重新存入redis
        redisTemplate.opsForValue().set(itemCart.getItemCartId(),itemCartStr);
        return Result.result(ResultEnum.SUCCESS, itemCart.getItemCartId(), "success");
    }

    /**
     * 登录，直接保存在redis中
     * @param userId
     * @param buyerItem
     * @return
     */
    @RequestMapping("cartAddIsLogin")
    public JSONObject cartAddIsLogin(@RequestParam(value = "userId") int userId, BuyerItem buyerItem) {
        // 判断有无添加过购物车
        ItemCart itemCart = (ItemCart) redisTemplate.opsForValue().get(userId + "cart");
        if (itemCart == null) {
            String cartId = UUID.randomUUID().toString();
            List<BuyerItem> buyerItems = new ArrayList<>();
            buyerItems.add(buyerItem);
            ItemCart newItemCart = new ItemCart(cartId, buyerItems,userId,0);
            // 将 结果直接存到redis中
            redisTemplate.opsForValue().set(userId + "cart", newItemCart);
            // 将结果写入库中
            String newbuyerItemsJson = JsonUtil.toJson(buyerItems);
            ItemCartVo itemCartVo = new ItemCartVo(cartId,userId,0, newbuyerItemsJson);
            this.itemCartService.addCartService(itemCartVo);
            return Result.result(ResultEnum.SUCCESS, cartId, "success");
        }

        // 第n次添加，或删减
        List<BuyerItem> buyerItemList = itemCart.getBuyerItems();
        LinkedHashSet<BuyerItem> newItemList = new LinkedHashSet<>();
        for (BuyerItem buyerItem1 : buyerItemList) {
            if (buyerItem.getItemId() == buyerItem1.getItemId()) {
                buyerItem1.setAmount(buyerItem.getAmount() + buyerItem1.getAmount());
            } else {
                newItemList.add(buyerItem);
            }
        }
        if (newItemList.size() == 1) {
            buyerItemList.addAll(newItemList);
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        itemCart.setUpdateDate(sd.format(new Date()));
        redisTemplate.opsForValue().set(userId + "cart", itemCart);
        String newbuyerItemsJson = JsonUtil.toJson(buyerItemList);
        ItemCartVo itemCartVoip = new ItemCartVo(itemCart.getItemCartId(),itemCart.getUserId(),0,newbuyerItemsJson);
        this.itemCartService.updateCartService(itemCartVoip);
        return Result.result(ResultEnum.SUCCESS, itemCart.getItemCartId(), "success");
    }


    /**
     * 未登录-登录 -将cookie的信息存到redis中,通常在未登录想要提交订单的时候调用
     */
    @RequestMapping("noLoginToLogin")
    public JSONObject noLoginToLogin(@RequestParam(value = "itemCart", defaultValue = "null") String itemCartId,@RequestParam(value = "userId") int userId,
                                     HttpServletResponse response,HttpServletRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // 从redis获取
        String itemCartOld = (String) this.redisTemplate.opsForValue().get(itemCartId);
        ItemCart itemCart = (ItemCart) JsonUtil.fromJson(itemCartOld, ItemCart.class);

        ItemCart itemCartRedis = (ItemCart) redisTemplate.opsForValue().get(userId + "cart");
        LinkedHashSet<BuyerItem> newBuyerList = new LinkedHashSet<>();
        // redis中无商品信息
        if (itemCartRedis == null) {
            if (itemCart.getBuyerItems().size() !=0) {
                itemCart.setUserId(userId);
                itemCart.setUpdateDate(simpleDateFormat.format(new Date()));
                // 先创建redis
                redisTemplate.opsForValue().set(userId + "cart", itemCart);
                redisTemplate.delete(itemCartId);
                //存库
                ItemCartVo itemCartVoip = new ItemCartVo(itemCart.getItemCartId(), itemCart.getUserId(), 0, JsonUtil.toJson(itemCart.getBuyerItems()));
                this.itemCartService.addCartService(itemCartVoip);
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
            if (newBuyerList.size() == 1) {
                itemCartRedis.getBuyerItems().addAll(newBuyerList);
            }
            // 存入redis中
            redisTemplate.opsForValue().set(userId + "cart", itemCartRedis);
            // 存库
            ItemCartVo itemCartVoip = new ItemCartVo(itemCartRedis.getItemCartId(), itemCartRedis.getUserId(), 0, JsonUtil.toJson(itemCartRedis.getBuyerItems()));
            this.itemCartService.updateCartService(itemCartVoip);
            return Result.result(ResultEnum.SUCCESS, "success");
        }

    }


    /**
     * 未登录情况下，展示购物车信息 redis
     *
     * @param itemCartId
     * @return
     */
    @RequestMapping("showCartNoLogin")
    public JSONObject showCartNoLogin(@RequestParam(value = "itemCart", defaultValue = "null") String itemCartId) {

        if (itemCartId.equals("null")) {
            return Result.result(ResultEnum.ERROR, "购物车为空", "error");
        }
       String itemCartOld = (String) redisTemplate.opsForValue().get(itemCartId);
        if (itemCartOld != null) {
            ItemCart itemCart = (ItemCart) JsonUtil.fromJson(itemCartOld, ItemCart.class);
            return Result.result(ResultEnum.SUCCESS, itemCart.getBuyerItems(), "success");
        }
        return Result.nullResult();
    }

    /**
     * 登录情况下 查看购物车
     * @param userId
     * @return
     */
    @RequestMapping("showCartLogin")
    public JSONObject showCartLogin(@RequestParam(value = "userId") int userId) {
        // 从redis获取购物车
        ItemCart itemCart = (ItemCart) redisTemplate.opsForValue().get(userId + "cart");
        if (itemCart == null) {
            return Result.result(ResultEnum.ERROR, "购物车为空", "error");
        }
        return Result.result(ResultEnum.SUCCESS, itemCart.getBuyerItems(), "success");
    }

    /**
     * 订单生成 -- 清空购物车
     * @param userId
     * @return
     */
    @RequestMapping("clearNoneCart")
    public JSONObject clearNoneCart(@RequestParam(value = "userId") int userId) {
        // 从redis 获取购物车
        ItemCart itemCart = (ItemCart) redisTemplate.opsForValue().get(userId+"cart");
        if (itemCart == null) {
            return Result.result(ResultEnum.ERROR,"购物车为空","error");
        }
        redisTemplate.delete(userId+"cart");
        return Result.result(ResultEnum.SUCCESS,"删除成功","success");
    }
 }
