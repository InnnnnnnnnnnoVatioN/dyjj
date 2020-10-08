package com.dy.dao;

import com.dy.bean.Payments;

import java.util.List;

public interface BasePaymentsDao {
    /**
     * 查询所有支付方式
     * @return
     */
    List<Payments> findAll();

    /**
     * 根据id查询单个支付方式
     * @param id
     * @return
     */
    Payments findById(int id);
}
