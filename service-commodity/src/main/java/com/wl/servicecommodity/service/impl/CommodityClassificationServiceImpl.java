package com.wl.servicecommodity.service.impl;

import com.wl.servicecommodity.dao.CommodityClassificationDao;
import com.wl.servicecommodity.entity.CommodityClassification;
import com.wl.servicecommodity.service.CommodityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityClassificationServiceImpl implements CommodityClassificationService {

    @Autowired
    private CommodityClassificationDao commodityClassificationDao;

    @Override
    public Boolean insertCommodityClassification(CommodityClassification commodityClassification) {
        CommodityClassification commodityClassifications = commodityClassificationDao.selectByCommonName(commodityClassification.getName());
        if (commodityClassifications == null) {
            this.commodityClassificationDao.insertCommodityClassification(commodityClassification);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCommodityClassification(Integer commodityId) {
        if (commodityId != null ) {
                this.commodityClassificationDao.deleteCommodityClassification(commodityId,3);
                return true;
        }
        return false;
    }

    @Override
    public Boolean updateCommodityClassification(CommodityClassification commodityClassification) {
        Boolean flag = this.commodityClassificationDao.updateCommodityClassification(commodityClassification);
        if (flag) {
            return true;
        }
        return false;
    }

    @Override
    public List<CommodityClassification> selectAllCommod(Integer status) {
        return this.commodityClassificationDao.selectAllCommod(status);
    }

    @Override
    public CommodityClassification selectByCommonId(Integer commodityId) {
        CommodityClassification commodityClassification = new CommodityClassification();
        if (commodityId != null) {
            commodityClassification  =this.commodityClassificationDao.selectByCommonId(commodityId);
        }
        return commodityClassification;
    }

    @Override
    public List<CommodityClassification> selectByCommonIdList(List<Integer> commodityId, Integer status) {
        List<CommodityClassification> commodityClassifications = new ArrayList<>();
        if (commodityId.size()>0) {
            commodityClassifications = this.commodityClassificationDao.selectByCommonIdList(commodityId, status);
        }
        return commodityClassifications;
    }
}
