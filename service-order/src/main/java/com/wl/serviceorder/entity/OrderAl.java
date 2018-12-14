package com.wl.serviceorder.entity;

import java.math.BigDecimal;

/**
 * 订单
 */
public class OrderAl {
    /**
     * 主键id
     */
    private int orderAId ;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 用户id
     */
    private int userId;

    /**
     * 地址id
      */
    private int areaId;
    /**
     * 支付总价
     */
    private BigDecimal payment;
    /**
     * 备注
     */
    private String content;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 订单状态 //0全部，1待付款，2待收货，3待评价，4待退货
     */
    private int status;



    public int getOrderAId() {
        return orderAId;
    }

    public void setOrderAId(int orderAId) {
        this.orderAId = orderAId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
