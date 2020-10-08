package com.dy.dao;

import com.dy.bean.Carts;
import com.dy.bean.Goods;

import java.util.List;

public interface BaseGoodsDao {
    /**
     * 根据购物车查询商品信息
     * @param cs
     * @return
     */
    List<Goods> findGoodsByCarts(List<Carts> cs);
    /**
     * 查询商品所有商品
     * @param orderBy 0正序 1倒序
     * @return 查询结果
     */
    List<Goods> findAll(int orderBy);
    /**
     * 根据一级分类查询商品
     * @param class1Id  一级分类编号
     * @param page  分页查询的页数
     * @param size  每页显示的数据数量
     * @param orderBy 0正序 1倒序
     * @return 查询结果
     */
    List<Goods> findGoodsByClass1(int class1Id,int page,int size,int orderBy);
    /**
     * 根据二级分类查询商品
     * @param class2Id  二级分类编号
     * @param page  分页查询的页数
     * @param size  每页显示的数据数量
     * @param orderBy 0正序 1倒序
     * @return 查询结果
     */
    List<Goods> findGoodsByClass2(int class2Id,int page,int size,int orderBy);
    /**
     * 根据用户输入的商品名称, 模糊查询商品
     * @param name  二级分类编号
     * @param page  分页查询的页数
     * @param size  每页显示的数据数量
     * @param orderBy 0正序 1倒序
     * @return 查询结果
     */
    List<Goods> findGoodsLikeName(String name,int page,int size,int orderBy);

    /**
     * 根据商品编号查询单个商品详情
     * @param id 要查询的商品编号
     * @return 查询的结果
     */
    Goods findGoodsById(int id);
}
