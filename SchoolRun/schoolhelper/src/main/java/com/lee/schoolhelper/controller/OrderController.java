package com.lee.schoolhelper.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lee.schoolhelper.dto.OrderDTO;
import com.lee.schoolhelper.dto.SearchBody;
import com.lee.schoolhelper.entity.Order;
import com.lee.schoolhelper.service.OrderService;
import com.lee.schoolhelper.utils.R;
import com.lee.schoolhelper.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @GetMapping("/get")
    public R  getAllOrder(){
        List<Order> orders = orderService.getAllOrder();
        if(orders == null || orders.size() < 1){
            return R.fail("");
        }
        List<OrderDTO> ordersDTO = orders.stream().map((order) -> {
            OrderDTO orderDTO = BeanUtil.toBean(order, OrderDTO.class);
            return orderDTO;
        }).collect(Collectors.toList());
        return R.success(null,ordersDTO);
    }
    @GetMapping("/getAll")
    public R getOrders(){
        List<Order> orders = orderService.getAllOrder();
        if(orders == null || orders.size() <= 0){
            return R.fail("");
        }
        return R.success("",orders);
    }

    @GetMapping("/getOrder/{id}")
    public R getOrderById(@PathVariable("id")Integer id){
        Order order = orderService.getOrderById(id);
        if(order == null){
            return R.fail("");
        }
        return R.success("",order);
    }
    @PostMapping("/add/{rId}/{cId}")
    public R addOrder(@RequestBody Order order,@PathVariable("rId") Integer rId,@PathVariable("cId") Integer cId){
        log.info("Order is {}",order);
        if(order == null){
            return R.fail("订单添加失败");
        }
        Integer res = orderService.addOrder(order,rId,cId);
        if(res <= 0){
            return R.fail("订单添加失败");
        }
        return R.success("订单添加成功",null);
    }
    @GetMapping("/getA/{acceptedUserId}")
    public R getOrderByAcceptedUserId(@PathVariable("acceptedUserId")Integer aId){
        List<Order> orders = orderService.getAllOrderByAcceptedUserId(aId);
        if(orders == null || orders.size() <= 0){
            return R.fail("");
        }
        return R.success("",orders);
    }

    @GetMapping("/getR/{releasedUserId}")
    public R getOrderByReleasedUserId(@PathVariable("releasedUserId")Integer rId){
        List<Order> orders = orderService.getAllOrderByReleasedUserId(rId);
        if(orders == null || orders.size() <= 0){
            return R.fail("");
        }
        return R.success("",orders);
    }

    @DeleteMapping("/deleteById/{orderId}")
    public R deleteOrderById(@PathVariable("orderId")Integer orderId){
        Integer res = orderService.deleteOrderById(orderId);
        if(res <= 0){
            return R.fail("撤销失败");
        }
        return R.success("撤销成功",null);
    }
    @GetMapping("/getByCid/{categoryId}")
    public R queryOrderByCategoryId(@PathVariable("categoryId")Integer id){
        List<Order> orders = orderService.queryOrderByCategoryId(id);
        if(orders == null || orders.size() <= 0) {
            R.fail(null);
        }
        List<OrderDTO> orderDtos = orders.stream().map(order -> {
           return BeanUtil.toBean(order, OrderDTO.class);
        }).collect(Collectors.toList());
        return R.success("",orderDtos);
    }
    @PostMapping("/search")
    public R searchOrder(@RequestBody SearchBody searchBody){
        List<Order> orders = orderService.searchOrder(searchBody.getContent());
        log.info("orders is {}",orders);
        if(orders == null || orders.size() <= 0){
            return R.fail("查询为空，请稍后再试");
        }
        List<OrderDTO> orderDtos = orders.stream().map(order -> {
            return BeanUtil.toBean(order, OrderDTO.class);
        }).collect(Collectors.toList());
        log.info("order dtos is {}",orderDtos.toString());
        return R.success("",orderDtos);
    }
    @PutMapping("/accept/{orderId}")
    public R acceptOrder(@PathVariable("orderId") Integer orderId){
        Integer res = orderService.acceptOrder(orderId);
        if(res == 2){
            return R.fail("请先完善个人信息");
        }
        if(res <= 0){
            return R.fail("订单接收失败");
        }
        return R.success("订单接收成功",null);
    }
    @PutMapping("/finish/{orderId}")
    public R finishOrder(@PathVariable("orderId")Integer orderId){
        Integer res = orderService.finishOrder(orderId);
        if(res <= 0){
            return R.fail("出现异常，请联系管理员操作订单");
        }
        return R.success("订单完成",null);
    }
    @GetMapping("/getTransState/{orderId}")
    public R getTransState(@PathVariable("orderId")Integer orderId){
        Integer res = orderService.getTransState(orderId);
        if(res == null){
            return R.fail("");
        }
        return R.success("",res);
    }
    @GetMapping("/count")
    public R count(){
        Integer res = orderService.countOrder();
        return R.success(null,res);
    }
    @GetMapping("/page/{pageNo}/{pageSize}")
    public R getOrderPage(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize")Integer pageSize){
        List<Order> orderPage = orderService.getOrderPage(pageNo, pageSize);
        if(orderPage == null || orderPage.size() <= 0){
            return R.fail("");
        }
        return R.success("",orderPage);
    }
    @PostMapping("/query")
    public R queryOrderByOrderSerial(@RequestBody SearchBody searchBody){
        if(searchBody == null){
            return R.fail("系统异常");
        }
        String content = searchBody.getContent();
        List<Order> orders = orderService.queryOrder(content);
        if(orders == null || orders.size() <= 0){
            return R.fail("");
        }
        return R.success("",orders);

    }
    @PutMapping("/updateCheck/{orderId}")
    public R check(@PathVariable("orderId") Integer orderId){
        Integer res = orderService.checkOrder(orderId);
        if(res <= 0){
            return R.fail("审核失败");
        }
        return  R.success("审核成功",null);
    }

}
