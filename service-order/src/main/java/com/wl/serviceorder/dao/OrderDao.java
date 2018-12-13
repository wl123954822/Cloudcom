package com.wl.serviceorder.dao;
import com.wl.serviceorder.dto.OrderAlDto;
import com.wl.serviceorder.dto.OrderDetilDto;
import com.wl.serviceorder.dto.OrderTj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanglei
 * @create 2017-12-27 10:37
 */
//订单管理
public interface OrderDao {

    List<OrderAlDto> getList();

    List<OrderDetilDto> getItemList(@Param("orderId") int orderId);

    String getNickName(@Param("userId") int userId);

    //订单总数
    int getOrderNum();

    //近一个月的订单数，以及销售额
    List<OrderTj> getMonthOrNum();

    //获取已处理的订单
    List<OrderAlDto> getOldOrder();

    //获取未处理的订单
    List<OrderAlDto> getNewOrder();

    //订单发货
    Boolean deLiver(int orderAlId);

    //显示订单条数
    int getOrderANum();
}
