package com.dy.dao;

import com.dy.bean.Order;
import com.dy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImp implements BaseOrderDao {
    private static final String SQL_INSERT = "INSERT INTO JJ_ORDERS(userId,status,money,paymentId,transportId,addressId,isAppraise,createTime)" +
            " VALUES(?,0,?,?,?,?,0,now())";
    private static final String SQL_FIND_BY_USERID = "SELECT * FROM JJ_ORDERS WHERE USERID=?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM JJ_ORDERS WHERE ID=?";

    /**
     * 订单的插入
     *
     * @param order
     * @return 新插入的订单的id
     */
    @Override
    public int insert(Order order) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            state.setInt(1,order.getUserId());
            state.setDouble(2,order.getMoney());
            state.setInt(3,order.getPaymentId());
            state.setInt(4,order.getTransportId());
            state.setInt(5,order.getAddressId());
            state.executeUpdate();
            result = state.getGeneratedKeys();
            // SELECT LAST_INSERT_ID():基于连接的
            // SELECT @@IDENTITY;基于数据库全局的
            if(result.next()){
                int id = result.getInt(1);
               return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return -1;
    }

    /**
     * 查询用户所有的订单
     *
     * @return
     */
    @Override
    public List<Order> findByUserId(int userId) {
        ArrayList<Order> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USERID);
            state.setInt(1,userId);
            result = state.executeQuery();
            while(result.next()){
                int id = result.getInt("id");
                int status = result.getInt("status");
                int paymentId = result.getInt("paymentId");
                int transportId = result.getInt("transportId");
                int addressId = result.getInt("addressId");
                int isAppraise = result.getInt("isAppraise");
                String expressNo = result.getString("expressNo");
                double money = result.getDouble("money");
                Timestamp createTime = result.getTimestamp("createTime");
                Order o = new Order(id,userId,status,money,paymentId,transportId,addressId,isAppraise,expressNo,createTime);
                data.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 查询单个订单信息
     *
     * @param id
     * @return
     */
    @Override
    public Order findById(int id) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_ID);
            state.setInt(1,id);
            result = state.executeQuery();
            if(result.next()){
                int userId = result.getInt("userId");
                int status = result.getInt("status");
                int paymentId = result.getInt("paymentId");
                int transportId = result.getInt("transportId");
                int addressId = result.getInt("addressId");
                int isAppraise = result.getInt("isAppraise");
                String expressNo = result.getString("expressNo");
                double money = result.getDouble("money");
                Timestamp createTime = result.getTimestamp("createTime");
                Order o = new Order(id,userId,status,money,paymentId,transportId,addressId,isAppraise,expressNo,createTime);
                return o;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }
}
