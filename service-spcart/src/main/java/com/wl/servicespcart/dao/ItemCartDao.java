package com.wl.servicespcart.dao;

import com.wl.servicespcart.entity.ItemCart;
import com.wl.servicespcart.vo.ItemCartVo;

/**
 * @author wanglei
 * 购物车 dao
 */
public interface ItemCartDao {

    int insertItemCart(ItemCartVo itemCart);

    int updateItemCart(ItemCartVo itemCart);
}
