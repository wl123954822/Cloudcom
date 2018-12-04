package com.wl.servicecommodity.entity;
/**
 * 商品分类
 * @author wl
 */
public class CommodityClassification {
	/**
	 * 主键id
	 */
	private int clid;
    /**
     * 备用字段
     */
	private int childId;
    /**
     * 分类名称
     */
	private String name;
    /**
     * 分类状态0-显示 3-删除
     */
	private int cstatus;

    public int getClid() {
        return clid;
    }

    public void setClid(int clid) {
        this.clid = clid;
    }

    public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public int getCstatus() {
        return cstatus;
    }

    public void setCstatus(int cstatus) {
        this.cstatus = cstatus;
    }
}
