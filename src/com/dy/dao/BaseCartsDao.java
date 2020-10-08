package com.dy.dao;

import com.dy.bean.Carts;

import java.util.List;

public interface BaseCartsDao {


    /**
     * 通过用户的编号 , 查询用户所有的购物车
     * @param userId 用户的编号
     * @return 购物车集合
     */
    List<Carts> findByUserId(int userId);

    /**
     * 通过用户的编号 , 查询用户所有的购物车
     * @param userId 用户的编号
     * @param userId 是否选中
     * @return 购物车集合
     */
    List<Carts> findByUserId(int userId,int isCheck);

    /**
     * 用于向carts表格中添加一行数据
     * @param userId 用户id
     * @param goodsId 商品id
     */
    void addCart(int userId,int goodsId);

    /**
     * 用于修改购物车的商品数量
     * @param userId 用户id
     * @param goodsId 商品id
     * @param num 增加或减少数量
     * @return 修改的结果, 200表示成功 -
     */
    int updateCartNum(int userId,int goodsId,int num);

    /**
     * 用于选中 或 取消选中购物车
     * @param userId 选中或取消选中的商品id
     * @param goodsId 选中或取消选中的商品id
     * @param isCheck 选中0  或 取消选中1
     * @return 处理的结果 ,200成功  -1表示失败
     */
    int cartCheck(int userId,int goodsId,int isCheck);

    /**
     * 用于从用户的购物车中 删除一个商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 删除的结果 200表示成功 -1表示失败
     */
    int cartDel(int userId,int goodsId);


    /**
     * 用于从用户的购物车中 删除一个商品
     * @param userId 用户id
     * @param isCheck 是否选中
     * @return 删除的结果 200表示成功 -1表示失败
     */
    int cartDelByCheck(int userId,int isCheck);

    /**
     * 用于向数据库中, 插入session中的购物车数据
     * @param userId 用户id
     * @param data session中的购物车集合
     * @return 插入结果
     */
    int insertSessionCarts(int userId,List<Carts> data);

}
