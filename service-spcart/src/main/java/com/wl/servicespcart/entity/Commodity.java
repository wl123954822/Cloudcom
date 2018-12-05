package com.wl.servicespcart.entity;

import java.math.BigDecimal;

/**
 * 商品
 * @author wl
 */
public class Commodity {
	/**
	 * 主键id
	 */
	private int id;
	/**
	 * 分类id
	 */
	private int cid;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 商品图片
	 */
	private String itemImage;
	/**
	 * 库存数量
	 */
	private Long itemNum;
	/**
	 * 商品进价格
	 */
	private BigDecimal purchasePrice;
	/**
	 * 商品售价格
	 */
	private BigDecimal itemPrice;
	/**
	 * 创建时间
	 */
	private String createDate;
	/**
	 * 创建者
	 */
	private String createName;
	/**
	 * 商品详情介绍
	 */
	private String itemIntroduce;
	/**
	 * 详情图
	 */
	private String itemIntroduceImage;
	/**
	 * 商品状态 1 在售 2 无货 3 下架
	 */
	private int status;
	/**
	 * 修改日期
	 */
	private String updateTime;
	/**
	 * 修改人
	 */
	private String updateName;
	/**
     * 减库存版本
	 */
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	private CommodityClassification commodityClassification;

	public CommodityClassification getCommodityClassification() {
		return commodityClassification;
	}

	public void setCommodityClassification(CommodityClassification commodityClassification) {
		this.commodityClassification = commodityClassification;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getItemIntroduce() {
        return itemIntroduce;
    }

    public void setItemIntroduce(String itemIntroduce) {
        this.itemIntroduce = itemIntroduce;
    }

    public String getItemIntroduceImage() {
        return itemIntroduceImage;
    }

    public void setItemIntroduceImage(String itemIntroduceImage) {
        this.itemIntroduceImage = itemIntroduceImage;
    }

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public Long getItemNum() {
		return itemNum;
	}

	public void setItemNum(Long itemNum) {
		this.itemNum = itemNum;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
}
