package com.wl.servicespcart.service.impl;

import com.wl.servicespcart.dao.ItemCartDao;
import com.wl.servicespcart.entity.ItemCart;
import com.wl.servicespcart.service.ItemCartService;
import com.wl.servicespcart.vo.ItemCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCartServiceImpl implements ItemCartService {

    @Autowired
    private ItemCartDao itemCartDao;

    @Override
    public boolean addCartService(ItemCartVo itemCart) {
        boolean flag = false;
        int reslut = this.itemCartDao.insertItemCart(itemCart);
        if (reslut > 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateCartService(ItemCartVo itemCart) {
        boolean flag = false;
        int reslut = this.itemCartDao.updateItemCart(itemCart);
        if (reslut > 1) {
            flag = true;
        }
        return flag;
    }
}
