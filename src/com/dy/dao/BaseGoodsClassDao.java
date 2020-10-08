package com.dy.dao;

import com.dy.bean.GoodsClass;

import java.util.List;

/**
 * 商品分类表的数据访问对象 模板
 */
public interface BaseGoodsClassDao {

    /**
     * 查询所有分类
     * @return
     */
    List<GoodsClass> findAll();

    /**
     * 查询商品分类 通过id
     * @param id
     * @return
     */
    GoodsClass findClassById(int id);


}
