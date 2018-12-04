package com.wl.servicecommodity.service;

import com.wl.servicecommodity.entity.Commodity;

import java.util.List;

public interface CommodityService {
    /**
     * 1.添加商品
     *
     * @param commodity
     * @return
     */
    Boolean insertCommodity(Commodity commodity);

    /**
     * 2.删除商品
     *
     * @param id
     * @return
     */
    Boolean deleteCommodity(int id);

    /**
     * 3.修改商品
     *
     * @param commodity
     * @return
     */
    Boolean updateCommodity(Commodity commodity);

    /**
     * 4.查询全部商品--status
     *
     * @param status
     * @return
     */
    List<Commodity> commodityAll(int status);

    /**
     * 5.根据id查询
     *
     * @param id
     * @return
     */
    Commodity commodById(int id);

    /**
     * 6.根据分类查询
     *
     * @param cid
     * @param status
     * @return
     */
    List<Commodity> commodByCid(int cid, int status);
}
