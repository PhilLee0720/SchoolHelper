package com.lee.schoolhelper.dao;

import com.lee.schoolhelper.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OrderMapper {
    Order getOrderById(@Param("orderId")Integer orderId);
    List<Order> getAllOrder();
    Integer addOrder(@Param("order") Order order,@Param("releasedUserId")Integer id,@Param("categoryId")Integer cId);
    List<Order> getAllOrderByAcceptedUserId(@Param("acceptedUserId")Integer acceptedUserId);
    List<Order> getAllOrderByReleasedUserId(@Param("releasedUserId")Integer releasedUserId);
    Integer deleteOrderById(@Param("orderId")Integer orderId);
    List<Order> queryOrderByCategoryId(@Param("categoryId")Integer categoryId);
    List<Order> searchOrder(@Param("content")String content);
    Integer acceptOrder(@Param("userId")Integer userId,@Param("orderId")Integer orderId);
    Integer finishOrder(@Param("orderId")Integer orderId);
    Integer getTransState(@Param("orderId")Integer orderId);
    Integer getOrderNumber();
    List<Order> getOrderPage(RowBounds rowBounds);
    List<Order> getOrderByOrderSerial(@Param("orderSerial") String orderSerial);
    Integer updateOrderCheckState(@Param("orderId")Integer orderId);
}
