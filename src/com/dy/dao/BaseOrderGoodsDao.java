package com.dy.dao;

import com.dy.bean.OrderGoods;

import java.util.List;

public interface BaseOrderGoodsDao {

    /**
     * 插入订单中的所有商品
     * @param goods
     * @return
     */
    boolean insert(List<OrderGoods> goods);

    /**
     * 根据订单id , 查询订单中的所有商品
     * @param orderId
     * @return
     */
    List<OrderGoods> findByOrderId(int orderId);

}
