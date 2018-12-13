package com.wl.serviceorder.service;


import com.wl.serviceorder.dto.OrderAlDto;
import com.wl.serviceorder.dto.OrderDetilDto;
import com.wl.serviceorder.dto.OrderTj;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanglei
 * @create 2017-12-27 10:43
 */
public interface OrderAlService {

    List<OrderAlDto> getList();

    List<OrderDetilDto> getItemList(int orderId);

    String getNickName(int userId);

    int OrderNum();

    List<OrderTj> MonthOrderNum();

    List<OrderAlDto> getOldList();

    List<OrderAlDto> getNewList();

    boolean delive(int orderAlId);

    int  getAlOrderNum();
 }
