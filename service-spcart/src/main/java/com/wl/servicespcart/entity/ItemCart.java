package com.wl.servicespcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
     * 创建时间
     */
    private String createDate;

    /**
     * 用户id , 未登录 默认为0
     */
    private int userId;

    /**
     * 购物车状态 ，0表示无商品，1-有商品-未进行下一步 2-有商品-进行下一步，购物车销毁
     */
    private int status;

    /**
     * 修改时间
     */
    private String updateDate;

    /**
     * 购物车商品id
     */
    private List<BuyerItem> buyerItems;

    public List<BuyerItem> getBuyerItems() {
        return buyerItems;
    }

    public void setBuyerItems(List<BuyerItem> buyerItems) {
        this.buyerItems = buyerItems;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
     */
    public void addItem(BuyerItem item) {
        // 判断是否包含同类型
        if (buyerItems.contains(item)) {
            // 追加数量
            for (BuyerItem buyerItems : buyerItems) {
                if (buyerItems.equals(item)) {
                    buyerItems.setAmount(item.getAmount()+buyerItems.getAmount());
                }
            }
        } else {
            buyerItems.add(item);
        }
    }
    /**
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

    public ItemCart() {
    }

    public ItemCart(String itemCartId, List<BuyerItem> buyerItems , int userId, int status) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        this.createDate = sd.format(new Date());
        this.itemCartId = itemCartId;
        this.buyerItems = buyerItems;
        this.userId = userId;
        this.status = status;
    }
}
