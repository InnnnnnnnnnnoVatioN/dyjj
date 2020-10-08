package com.dy.service;

import com.dy.bean.OrderGoods;
import com.dy.dao.BaseOrderGoodsDao;
import com.dy.dao.OrderGoodsDaoImp;

import java.util.List;

public class OrderGoodsService {

    private static BaseOrderGoodsDao dao = new OrderGoodsDaoImp();
    /**
     * 插入订单中的所有商品
     * @param goods
     * @return
     */
    public static boolean insert(List<OrderGoods> goods){
        return dao.insert(goods);
    }

    /**
     * 根据订单id , 查询订单中的所有商品
     * @param orderId
     * @return
     */
    public static List<OrderGoods> findByOrderId(int orderId){
        return dao.findByOrderId(orderId);
    }
}
