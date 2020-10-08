package com.dy.service;

import com.dy.bean.Order;
import com.dy.dao.BaseOrderDao;
import com.dy.dao.OrderDaoImp;

import java.util.List;

public class OrderService {
    private static BaseOrderDao dao = new OrderDaoImp();
    /**
     * 订单的插入
     * @param order
     * @return 新插入的订单的id
     */
    public static int insert(Order order){
        return dao.insert(order);
    }

    /**
     * 查询用户所有的订单
     * @return
     */
    public static List<Order> findByUserId(int userId){
        return dao.findByUserId(userId);
    }

    /**
     * 查询单个订单信息
     * @param id
     * @return
     */
    public static Order findById(int id){
        return dao.findById(id);
    }
}
