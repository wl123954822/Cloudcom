package com.wl.serviceorder.controller;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.serviceorder.dto.OrderAlDto;
import com.wl.serviceorder.dto.OrderDetilDto;
import com.wl.serviceorder.dto.OrderTj;
import com.wl.serviceorder.service.OrderAlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author wanglei
 * @create 2017-12-27 10:47
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderAlService orderAlService;

    //所有的订单列表
    @RequestMapping("/list")
    public Map<String,Object> getList(@RequestParam("pagsSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam(value = "status",defaultValue = "0") int status) {
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(pageNumber,pageSize);
        List<OrderAlDto> list = orderAlService.getList();
        PageInfo<OrderAlDto> info = new PageInfo<>(list);
        for (OrderAlDto orderAlDto:list){
            String time = orderAlDto.getCreateDate();
            info.setOrderBy(time);
        }
        map.put("rows",info.getList());
        map.put("total",info.getTotal());
        return map;
    }


    //订单详情页
    @RequestMapping("/orderAlLis")
    public Map<String, Object> getAll(@RequestParam("orderId") int orderId) {
        Map<String, Object> map = new HashMap<>();
        //商品
        List<OrderDetilDto> orderDetilDtos = orderAlService.getItemList(orderId);
        map.put("list", orderDetilDtos);
        return map;
    }

    //订单统计
    @RequestMapping("/orderTj")
    public Map<String,Object> getOrderNumPay(){
        Map<String,Object> map = new HashMap<>();

        List<OrderTj> list = orderAlService.MonthOrderNum();
        int orderNum = orderAlService.OrderNum();

        for (OrderTj orderTj: list) {
            orderTj.setOrderNum(orderNum);
        }

        map.put("list",list);

        return map;
    }

    //已处理订单
    @RequestMapping("/oldList")
    public Map<String,Object> getOldList(int pageSize, int pageNumber){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(pageNumber,pageSize);
        List<OrderAlDto> list = orderAlService.getOldList();
        PageInfo<OrderAlDto> info = new PageInfo<>(list);
        for (OrderAlDto orderAlDto:list){
            String time = orderAlDto.getCreateDate();
            info.setOrderBy(time);
        }

        map.put("rows",info.getList());
        map.put("total",info.getTotal());
        return map;
    }

    //已处理订单
    @RequestMapping("/newList")
    public Map<String,Object> getNewList(int pageSize, int pageNumber){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(pageNumber,pageSize);
        List<OrderAlDto> list = orderAlService.getNewList();
        PageInfo<OrderAlDto> info = new PageInfo<>(list);
        for (OrderAlDto orderAlDto:list){
            String time = orderAlDto.getCreateDate();
            info.setOrderBy(time);
        }

        map.put("rows",info.getList());
        map.put("total",info.getTotal());
        return map;
    }

    //卖家发货
    @RequestMapping("/deliver")
    public Map<String,Object> deliver(int orderAlId){
        Map<String,Object> map = new HashMap<>();

        if (orderAlId !=0){
            if (orderAlService.delive(orderAlId)){
                map.put("status","success");
                map.put("text","更新成功");
            }
        }else {
            map.put("status","error");
            map.put("text","参数为空");
        }
        return map;
    }

    //显示未完成的订单条数
    @RequestMapping("/showOrAl")
    public Map<String,Object> showOal(){
        Map<String,Object> map = new HashMap<>();

        int num = orderAlService.getAlOrderNum();

        map.put("status","success");
        map.put("num",num);
        
        return map;
    }
}
