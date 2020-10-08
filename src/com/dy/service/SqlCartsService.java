package com.dy.service;

import com.dy.bean.Carts;
import com.dy.bean.User;
import com.dy.dao.BaseCartsDao;
import com.dy.dao.CartsDaoImp;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SqlCartsService implements BaseCartsService {
    private static BaseCartsDao dao = new CartsDaoImp();
    private int getUserId(HttpSession session){
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        return userId;
    }




    /**
     * 通过用户的编号 , 查询用户所有的购物车
     * @return 购物车集合
     */
    public List<Carts> findByUserId(HttpSession session,int isCheck){
        return dao.findByUserId(getUserId(session),isCheck);
    }


    /**
     * 对于商品的增加
     *
     * @param session
     * @param goodsId
     */
    @Override
    public void addCart(HttpSession session, int goodsId) {
        dao.addCart(getUserId(session),goodsId);
    }

    /**
     * 对于商品数量的修改
     *
     * @param session
     * @param goodsId 要修改的商品id
     * @param num     -1 或 1
     */
    @Override
    public int updateCartNum(HttpSession session, int goodsId, int num){
        return dao.updateCartNum(getUserId(session),goodsId,num);
    }

    /**
     * 用于选中 或 取消选中购物车
     *
     * @param session
     * @param goodsId 选中或取消选中的商品id
     * @param isCheck 选中0  或 取消选中1
     * @return 处理的结果 ,200成功  -1表示失败
     */
    @Override
    public int cartCheck(HttpSession session, int goodsId, int isCheck){
        return dao.cartCheck(getUserId(session),goodsId,isCheck);
    }

    /**
     * 用于从购物车列表中 移除一个商品的购物车
     * @param session
     * @param goodsId 要移除的商品id
     * @return 200 成功 -1 表示失败
     */
    @Override
    public int cartDel(HttpSession session, int goodsId) {
        return dao.cartDel(getUserId(session),goodsId);
    }

    /**
     * 用于从用户的购物车中 删除一个商品
     * @param isCheck 是否选中
     * @return 删除的结果 200表示成功 -1表示失败
     */
    public int cartDelByCheck(HttpSession session,int isCheck){
        return dao.cartDelByCheck(getUserId(session),isCheck);
    }


    /**
     * 根据用户id  查询他所有的购物车数据
     * @param userId
     * @return
     */
    public List<Carts> findByUserId(int userId){
        return dao.findByUserId(userId);
    }


    public int insertSessionCarts(int userId, List<Carts> data){
        return dao.insertSessionCarts(userId,data);
    }
}
