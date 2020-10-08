package com.dy.dao;

import com.dy.bean.Transports;

import java.util.List;

public interface BaseTransportsDao {

    /**
     * 查询所有配送方式
     * @return
     */
    List<Transports> findAll();

    /**
     * 根据id查询单个配送方式
     * @param id
     * @return
     */
    Transports findById(int id);
}
