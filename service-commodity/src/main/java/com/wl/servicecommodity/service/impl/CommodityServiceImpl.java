package com.wl.servicecommodity.service.impl;

import com.wl.servicecommodity.dao.CommodityDao;
import com.wl.servicecommodity.entity.Commodity;
import com.wl.servicecommodity.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityDao commodityDao;


    @Override
    public Boolean insertCommodity(Commodity commodity) {
        Boolean flag = false;
        Commodity commoditys = this.commodityDao.commodByName(commodity.getItemName(),commodity.getStatus());
        if (commoditys == null) {
            this.commodityDao.insertCommodity(commodity);
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean deleteCommodity(int id) {
            Boolean flag = false;
        if (id != 0 ) {
            this.commodityDao.deleteCommodity(id,3);
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean updateCommodity(Commodity commodity) {
            Boolean flag = false;
        if (commodityDao.updateCommodity(commodity)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Commodity> commodityAll(int status) {
        List<Commodity> list = this.commodityDao.commodityAll(status);
        return list;
    }

    @Override
    public Commodity commodById(int id) {
        Commodity commodity = new Commodity();
        if (id != 0) {
            commodity = this.commodityDao.commodById(id);
        }
        return commodity;
    }

    @Override
    public List<Commodity> commodByCid(int cid, int status) {
        List<Commodity>  list = this.commodityDao.commodByCid(cid,status);
        return list;
    }
}
