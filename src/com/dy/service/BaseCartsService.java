package com.dy.service;

import javax.servlet.http.HttpSession;

public interface BaseCartsService {

    /**
     * 对于商品的增加
     * @param session
     * @param goodsId
     */
    void addCart(HttpSession session, int goodsId);

    /**
     * 对于商品数量的修改
     * @param session
     * @param goodsId 要修改的商品id
     * @param num -1 或 1
     */
    int updateCartNum(HttpSession session,int goodsId,int num);

    /**
     * 用于选中 或 取消选中购物车
     * @param session
     * @param goodsId 选中或取消选中的商品id
     * @param isCheck 选中0  或 取消选中1
     * @return 处理的结果 ,200成功  -1表示失败
     */
    int cartCheck(HttpSession session,int goodsId,int isCheck);


    /**
     * 用于从购物车列表中 移除一个商品的购物车
     * @param session
     * @param goodsId 要移除的商品id
     * @return 200 成功 -1 表示失败
     */
    int cartDel(HttpSession session,int goodsId);



}
