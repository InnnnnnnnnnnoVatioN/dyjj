package com.dy.dao;

import com.dy.bean.Payments;
import com.dy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentsDaoImp implements BasePaymentsDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM JJ_PAYMENTS";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM JJ_PAYMENTS WHERE ID=?";

    /**
     * 查询所有支付方式
     *
     * @return
     */
    @Override
    public List<Payments> findAll() {
        ArrayList<Payments> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_ALL);
            result = state.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String img = result.getString("img");
                Payments pay = new Payments(id,name,img);
                data.add(pay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 根据id查询单个支付方式
     *
     * @param id
     * @return
     */
    @Override
    public Payments findById(int id) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_ID);
            state.setInt(1,id);
            result = state.executeQuery();
            if (result.next()){
                String name = result.getString("name");
                String img = result.getString("img");
                Payments pay = new Payments(id,name,img);
                return pay;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }
}
