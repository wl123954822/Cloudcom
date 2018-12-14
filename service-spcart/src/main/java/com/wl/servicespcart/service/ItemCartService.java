package com.wl.servicespcart.service;

import com.wl.servicespcart.entity.ItemCart;
import com.wl.servicespcart.vo.ItemCartVo;

/**
 * 购物车 service
 * @author wanglei
 */
public interface ItemCartService {

    boolean addCartService(ItemCartVo itemCart);

    boolean updateCartService(ItemCartVo itemCart);
}
