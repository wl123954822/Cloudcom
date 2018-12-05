package com.wl.servicecommodity.service.impl;

import com.wl.servicecommodity.dao.CommodityDao;
import com.wl.servicecommodity.service.CommoditySurplusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommoditySurplusServiceImpl implements CommoditySurplusService {

    private static final  Logger  log = LoggerFactory.getLogger(CommoditySurplusServiceImpl.class);


    @Autowired
    private CommodityDao commodityDao;


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean addItemNum(int itemId, int addItemNum) {
        boolean flag = false;
        if (addItemNum >0 && itemId> 0) {
            int result = this.commodityDao.addItemNum(itemId, addItemNum);
            if (result >0) {
                flag = true;
            } else {
                log.info("添加库存失败");
                throw new RuntimeException("添加库存失败");
            }
        } else {
            throw new RuntimeException("添加库存失败");
        }
        return flag;
    }

    @Override
    public Boolean delItemNum(int itemId, int delItemNum, int oldVersion) {
        Boolean flag = false;
        if (itemId >0 && delItemNum >0) {
            // 先判断库中的数量 大于0执行，更新操作
            long itemNum = this.commodityDao.getItemNum(itemId);
            log.info("itemNum数量 ：" + itemNum);
            if (itemNum > 0 ){
                int result = this.commodityDao.delItemNum(itemId, delItemNum, oldVersion);
                log.info("库存：" + result);
                flag = true;
            }
        }
        return flag;
    }
}
