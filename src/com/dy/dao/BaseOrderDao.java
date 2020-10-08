package com.dy.dao;

import com.dy.bean.Order;

import java.util.List;

public interface BaseOrderDao {
    /**
     * 订单的插入
     * @param order
     * @return 新插入的订单的id
     */
    int insert(Order order);

    /**
     * 查询用户所有的订单
     * @return
     */
    List<Order> findByUserId(int userId);

    /**
     * 查询单个订单信息
     * @param id
     * @return
     */
    Order findById(int id);
}
