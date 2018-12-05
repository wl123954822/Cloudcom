package com.wl.servicecommodity.service;


/**
 * 库存管理的service
 * @author wanglei
 */
public interface CommoditySurplusService {

    /**
     * 8.增加库存
     *
     * @param itemId
     * @param addItemNum
     * @return
     */
    Boolean addItemNum( int itemId,int addItemNum);

    /**
     * 9.减少库存
     * @param itemId
     * @param delItemNum
     * @return
     */
    Boolean delItemNum(int itemId, int delItemNum, int oldVersion);
}
