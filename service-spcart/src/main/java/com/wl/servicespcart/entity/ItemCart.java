package com.wl.servicespcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车实体
 * @author wanglei
 */
public class ItemCart {

    private int itemCartId;

    private List<BuyerItem> items = new ArrayList<>();

    /**
     * 添加购物项到购物车
     * @param item
     */
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

    /**
     * 商品数量
     * @return
     */
    @JsonIgnore
    public Integer getProductAmount() {
        Integer result = 0;
        for (BuyerItem buyerItem : items) {
            result += buyerItem.getAmount();
        }
        return result;
    }


    /**
     * 商品金额
     */
    @JsonIgnore
    public BigDecimal getItemPrice() {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (BuyerItem buyerItem : items) {
            bigDecimal.add(buyerItem.getPrice());
        }
        return bigDecimal;
    }


    public int getItemCartId() {
        return itemCartId;
    }

    public void setItemCartId(int itemCartId) {
        this.itemCartId = itemCartId;
    }

    public List<BuyerItem> getItems() {
        return items;
    }

    public void setItems(List<BuyerItem> items) {
        this.items = items;
    }
}
