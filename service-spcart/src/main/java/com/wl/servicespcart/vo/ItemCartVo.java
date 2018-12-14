package com.wl.servicespcart.vo;

import com.wl.servicespcart.entity.BuyerItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 购物车实体
 * @author wanglei
 */
public class ItemCartVo {

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
    private String buyerItems;

    public String getBuyerItems() {
        return buyerItems;
    }

    public void setBuyerItems(String buyerItems) {
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

    public ItemCartVo() {

    }

    public ItemCartVo(String itemCartId, int userId, int status,String buyerItems) {
        this.itemCartId = itemCartId;
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        this.createDate = sd.format(new Date());
        this.userId = userId;
        this.status = status;
        this.updateDate = sd.format(new Date());
        this.buyerItems = buyerItems;
    }
}
