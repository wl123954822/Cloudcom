package com.wl.servicecommodity.dao;

import com.wl.servicecommodity.entity.CommodityClassification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类管理dao
 * 1.分类添加
 * 2.分类删除
 * 3.分类修改
 * 4.分类查询-all
 * 5.分类查询-byId
 * 6.分类查询-byIdList
 * 7.分类查询-byName
 * @author wanglei
 */
public interface CommodityClassificationDao {
    /**
     * 1.分类添加
     */
    Boolean insertCommodityClassification(CommodityClassification commodityClassification);
    /**
     * 2.分类删除--假删除
     */
    Boolean deleteCommodityClassification(@Param("id") Integer commodityId,@Param("status") int status);
    /**
     * 3.分类修改
     */
    Boolean updateCommodityClassification(CommodityClassification commodityClassification);
    /**
     * 4.分类查询-all
     */
    List<CommodityClassification> selectAllCommod(@Param("status") Integer status);
    /**
     * 5.分类查询-byId
     */
    CommodityClassification selectByCommonId(Integer commodityId);
    /**
     * 6.分类查询-byIdList
     */
    List<CommodityClassification> selectByCommonIdList(List<Integer> commodityId, @Param("status") Integer status);

    /**
     * 7.分类查询-byName
     * @param commodityName
     * @return
     */
    CommodityClassification selectByCommonName(String commodityName);
}
