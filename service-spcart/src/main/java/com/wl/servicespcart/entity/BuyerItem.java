package com.wl.servicespcart.entity;

import java.math.BigDecimal;

/**
 * 购物车
 */
public class BuyerItem {
    /**
     * 用户
     */
    private int userId;
    /**
     * 是否有货
     */
    private Boolean isHave = true;
    /**
     * 订购数量
     */
    private Integer amount = 1;
    /**
     * 价格
     */
    private BigDecimal price ;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getHave() {
        return isHave;
    }

    public void setHave(Boolean have) {
        isHave = have;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
