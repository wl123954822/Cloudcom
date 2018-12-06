package com.wl.servicespcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 购物车实体
 * @author wanglei
 */
public class ItemCart {

    /**
     * 购物车主键id
     */
    private String itemCartId;

    /**
     * 购物车商品id
     */
    private List<String> itemIds;

    private String createDate;

    private int userId;

    public List<String> getItemIds() {
        return itemIds;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getItemCartId() {
        return itemCartId;
    }

    public void setItemCartId(String itemCartId) {
        this.itemCartId = itemCartId;
    }

    /*private List<BuyerItem> items = new ArrayList<>();

    *//**
     * 添加购物项到购物车
     * @param item
     *//*
    public void addItem(BuyerItem item) {
        // 判断是否包含同类型
        if (items.contains(item)) {
            // 追加数量
            for (BuyerItem buyerItems : items) {
                if (buyerItems.equals(item)) {
                    buyerItems.setAmount(item.getAmount()+buyerItems.getAmount());
                }
            }
        } else {
            items.add(item);
        }
    }

    *//**
     * 商品数量
     * @return
     *//*
    @JsonIgnore
    public Integer getProductAmount() {
        Integer result = 0;
        for (BuyerItem buyerItem : items) {
            result += buyerItem.getAmount();
        }
        return result;
    }


    *//**
     * 商品金额
     *//*
    @JsonIgnore
    public BigDecimal getItemPrice() {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (BuyerItem buyerItem : items) {
            bigDecimal.add(buyerItem.getPrice());
        }
        return bigDecimal;
    }

*/


    /*public List<BuyerItem> getItems() {
        return items;
    }

    public void setItems(List<BuyerItem> items) {
        this.items = items;
    }*/
}
