package com.wl.servicespcart.vo;

import com.alibaba.fastjson.JSONArray;
import com.wl.servicespcart.entity.BuyerItem;
import com.wl.servicespcart.entity.ItemCart;
import com.wl.servicespcart.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class tes {

    public static void main(String[] args) {
        List<BuyerItem> list = new ArrayList<>();


        BuyerItem buyerItem = new BuyerItem();
        buyerItem.setAmount(200);
        buyerItem.setItemId(1);
        buyerItem.setItemName("lala");
        list.add(buyerItem);
        BuyerItem buyerItem2 = new BuyerItem();
        buyerItem2.setAmount(200);
        buyerItem2.setItemId(2);
        buyerItem2.setItemName("la");
        list.add(buyerItem2);

        String newbuyerItemsJson = JsonUtil.toJson(list);
        System.out.println(newbuyerItemsJson);


        List<BuyerItem> liss = JSONArray.parseArray(newbuyerItemsJson,BuyerItem.class);

        //mmmm
        System.out.println(liss.toString());
    }
}
