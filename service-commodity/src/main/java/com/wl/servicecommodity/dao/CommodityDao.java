package com.wl.servicecommodity.dao;

import com.wl.servicecommodity.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanglei
 * 商品管理dao
 * 1.添加商品
 * 2.删除商品
 * 3.修改商品
 * 4.查询全部商品--status
 * 5.根据id查询
 * 6.根据分类查询
 * 7.根据名称查询
 */
public interface CommodityDao {

    /**
     * 1.添加商品
     * @param commodity
     * @return
     */
    Boolean insertCommodity(Commodity commodity);

    /**
     * 2.删除商品
     * @param id
     * @param status
     * @return
     */
    Boolean deleteCommodity(int id,int status);

    /**
     * 3.修改商品
     * @param commodity
     * @return
     */
    Boolean updateCommodity(Commodity commodity);

    /**
     * 4.查询全部商品--status
     * @param status
     * @return
     */
    List<Commodity> commodityAll(@Param("status") int status);

    /**
     * 5.根据id查询
     * @param id
     * @return
     */
    Commodity commodById(@Param("id") int id);

    /**
     * 6.根据分类查询
     * @param cid
     * @param status
     * @return
     */
    List<Commodity> commodByCid(@Param("cid") int cid , @Param("status") int status);

    /**
     * 7.根据名称查询
     * @param itemName
     * @return
     */
    Commodity commodByName(@Param("itemName") String itemName,@Param("status") int status);

}
