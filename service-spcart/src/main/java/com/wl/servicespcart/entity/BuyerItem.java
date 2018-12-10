package com.wl.servicespcart.entity;

import java.math.BigDecimal;

/**
 * 购物车商品
 */
public class BuyerItem {
    /**
     * 商品id
     */
    private int itemId;
    /**
     * 商品名称
     */
   private String itemName;
    /**
     * 订购数量
     */
    private Integer amount = 1;
    /**
     * 价格
     */
    private BigDecimal price ;

    /**
     * 商品介绍
     */
    private String itemIntroduce;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getItemIntroduce() {
        return itemIntroduce;
    }

    public void setItemIntroduce(String itemIntroduce) {
        this.itemIntroduce = itemIntroduce;
    }


    @Override
    public String toString() {
        return "BuyerItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", itemIntroduce='" + itemIntroduce + '\'' +
                '}';
    }
}
