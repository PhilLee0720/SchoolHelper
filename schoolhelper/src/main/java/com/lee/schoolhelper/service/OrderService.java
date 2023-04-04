package com.lee.schoolhelper.service;

import com.lee.schoolhelper.entity.Order;

import java.util.List;

/**
 * 获取所有订单
 */
public interface OrderService {
    //通过id获取订单
    Order getOrderById(Integer id);
    //获取所有订单
    List<Order> getAllOrder();

    Integer addOrder(Order order,Integer rId,Integer cId);

    List<Order> getAllOrderByAcceptedUserId(Integer acceptedUserId);

    List<Order> getAllOrderByReleasedUserId(Integer releasedUserId);

    Integer deleteOrderById(Integer orderId);

    List<Order> queryOrderByCategoryId(Integer categoryId);

    List<Order> searchOrder(String content);

    Integer acceptOrder(Integer orderId);

    Integer finishOrder(Integer orderId);

    Integer getTransState(Integer orderId);

    Integer countOrder();

    List<Order> getOrderPage(Integer pageNo,Integer pageSize);

    List<Order> queryOrder(String orderSerial);

    Integer checkOrder(Integer orderId);
}
