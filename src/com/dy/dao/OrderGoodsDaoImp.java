package com.dy.dao;

import com.dy.bean.Order;
import com.dy.bean.OrderGoods;
import com.dy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderGoodsDaoImp implements BaseOrderGoodsDao {
    private static final String SQL_FIND_BY_ORDERID = "SELECT * FROM JJ_ORDER_GOODS WHERE ORDERID=?";

    /**
     * 插入订单中的所有商品
     *
     * @param goods
     * @return
     */
    @Override
    public boolean insert(List<OrderGoods> goods) {
        Connection conn = DruidUtil.getConnection();
        Statement state = null;
        try {
            state = conn.createStatement();
            for(int i=0;i<goods.size();i++){
                OrderGoods og = goods.get(i);
                state.addBatch("insert into jj_order_goods(orderId,goodsId,goodsNum,goodsPrice,goodsName,goodsImg)" +
                        " values("+og.getOrderId()+","+og.getGoodsId()+","+og.getGoodsNum()+","+og.getGoodsPrice()+",'"+og.getGoodsName()+"','"+og.getGoodsImg()+"')");
            }
            state.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return true;
    }

    /**
     * 根据订单id , 查询订单中的所有商品
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderGoods> findByOrderId(int orderId) {
        ArrayList<OrderGoods> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_ORDERID);
            state.setInt(1,orderId);
            result = state.executeQuery();
            while(result.next()){
                //orderId,goodsId,goodsNum,goodsPrice,goodsName,goodsImg
                int id = result.getInt("id");
                int goodsId = result.getInt("goodsId");
                int goodsNum = result.getInt("goodsNum");
                double goodsPrice = result.getDouble("goodsPrice");
                String goodsName = result.getString("goodsName");
                String goodsImg = result.getString("goodsImg");
                OrderGoods og = new OrderGoods(id,orderId,goodsId,goodsNum,goodsPrice,goodsName,goodsImg);
                data.add(og);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }
}
