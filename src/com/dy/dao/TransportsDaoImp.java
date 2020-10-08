package com.dy.dao;

import com.dy.bean.Payments;
import com.dy.bean.Transports;
import com.dy.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportsDaoImp implements BaseTransportsDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM JJ_TRANSPORTS";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM JJ_TRANSPORTS WHERE ID=?";

    /**
     * 查询所有配送方式
     *
     * @return
     */
    @Override
    public List<Transports> findAll() {
        ArrayList<Transports> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_ALL);
            result = state.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                Transports pay = new Transports(id,name);
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
     * 根据id查询单个配送方式
     *
     * @param id
     * @return
     */
    @Override
    public Transports findById(int id) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_ID);
            state.setInt(1,id);
            result = state.executeQuery();
            while (result.next()){
                String name = result.getString("name");
                Transports pay = new Transports(id,name);
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
