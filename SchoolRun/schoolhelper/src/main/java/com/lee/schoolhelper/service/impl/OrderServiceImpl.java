package com.lee.schoolhelper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.lee.schoolhelper.dao.OrderMapper;
import com.lee.schoolhelper.dao.UserMapper;
import com.lee.schoolhelper.dto.OrderDTO;
import com.lee.schoolhelper.entity.Order;
import com.lee.schoolhelper.entity.User;
import com.lee.schoolhelper.service.OrderService;
import com.lee.schoolhelper.utils.R;
import com.lee.schoolhelper.utils.UserHolder;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl  implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public Order getOrderById(Integer id) {
        Order order = orderMapper.getOrderById(id);
        return order;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orders = orderMapper.getAllOrder();
        return orders;
    }

    @Override
    public Integer addOrder(Order order,Integer oId,Integer cId) {
        String serial = RandomUtil.randomString(19);
        order.setOrderSerial(serial);
        Integer result = orderMapper.addOrder(order,oId,cId);
        return result;
    }


    @Override
    public List<Order> getAllOrderByAcceptedUserId(Integer acceptedUserId) {
        List<Order> orders = orderMapper.getAllOrderByAcceptedUserId(acceptedUserId);
        return orders;
    }

    @Override
    public List<Order> getAllOrderByReleasedUserId(Integer releasedUserId) {
        List<Order> orders = orderMapper.getAllOrderByReleasedUserId(releasedUserId);
        return orders;
    }

    @Override
    public Integer deleteOrderById(Integer orderId) {

        Integer res = orderMapper.deleteOrderById(orderId);
        return  res;
    }

    @Override
    public List<Order> queryOrderByCategoryId(Integer categoryId) {
        List<Order> orders = orderMapper.queryOrderByCategoryId(categoryId);
        return orders;
    }

    @Override
    public List<Order> searchOrder(String content) {
        List<Order> orders = orderMapper.searchOrder(content);
        return orders;
    }

    @Override
    public Integer acceptOrder(Integer orderId) {
        String phoneNumber = UserHolder.get().getPhoneNumber();
        if(phoneNumber == null || phoneNumber.equals("未填写")){
            return 2;
        }
        Integer res = orderMapper.acceptOrder(UserHolder.get().getId(),orderId);
        return res;
    }

    @Override
    public Integer finishOrder(Integer orderId) {
        Integer res = orderMapper.finishOrder(orderId);
        return res;
    }

    @Override
    public Integer getTransState(Integer orderId) {
        Integer res = orderMapper.getTransState(orderId);
        return res;
    }

    @Override
    public Integer countOrder() {
        Integer orderNumber = orderMapper.getOrderNumber();
        return orderNumber;

    }

    @Override
    public List<Order> getOrderPage(Integer pageNo,Integer pageSize) {
        Integer startIndex = (pageNo-1) * pageSize;
        RowBounds rowBounds = new RowBounds(startIndex, pageSize);
        List<Order> orderPage = orderMapper.getOrderPage(rowBounds);
        return orderPage;
    }

    @Override
    public List<Order> queryOrder(String orderSerial) {
        List<Order> order = orderMapper.getOrderByOrderSerial(orderSerial);
        return order;
    }

    @Override
    public Integer checkOrder(Integer orderId) {
        Integer res = orderMapper.updateOrderCheckState(orderId);
        return res;
    }


}
