package com.wl.servicecommodity.service;

import com.wl.servicecommodity.entity.CommodityClassification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanglei
 */
public interface CommodityClassificationService {
    /**
     * 1.分类添加
     */
    Boolean insertCommodityClassification(CommodityClassification commodityClassification);

    /**
     * 2.分类删除--假删除
     */
    Boolean deleteCommodityClassification(@Param("id") Integer commodityId);

    /**
     * 3.分类修改
     */
    Boolean updateCommodityClassification(CommodityClassification commodityClassification);

    /**
     * 4.分类查询-all
     */
    List<CommodityClassification> selectAllCommod(Integer status);

    /**
     * 5.分类查询-byId
     */
    CommodityClassification selectByCommonId(Integer commodityId);

    /**
     * 6.分类查询-byIdList
     */
    List<CommodityClassification> selectByCommonIdList(List<Integer> commodityId, Integer status);
}
