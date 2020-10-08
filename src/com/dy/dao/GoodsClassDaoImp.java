package com.dy.dao;

import com.dy.bean.GoodsClass;
import com.dy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsClassDaoImp implements BaseGoodsClassDao{
    private static final String SQL_FIND_ALL = "SELECT * FROM JJ_CLASS";
    private static final String SQL_FIND_CLASS_BY_ID = "SELECT * FROM JJ_CLASS WHERE ID=?";

    /**
     * 查询所有分类
     *
     * @return
     */
    @Override
    public List<GoodsClass> findAll() {
        //0.    创建一个List集合, 用于存储查询的结果
        List<GoodsClass> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    连接数据库
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句
            state = conn.prepareStatement(SQL_FIND_ALL);
            //3.    执行SQL语句 , 并获取结果集
            result = state.executeQuery();
            //4.    遍历结果集, 将结果集中的每一行数据 封装为一个GoodsClass对象
            while(result.next()){
                int id = result.getInt("id");
                int parentId = result.getInt("parentId");
                String className = result.getString("className");
                //将结果集中的每一行数据 封装为一个GoodsClass对象
                GoodsClass goodsClass = new GoodsClass(id,parentId,className);
                //5.    将查询到的每一个分类对象,  存储到List集合中
                data.add(goodsClass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6.    释放
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 查询商品分类 通过id
     *
     * @param id
     * @return
     */
    @Override
    public GoodsClass findClassById(int id) {

        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    连接数据库
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句
            state = conn.prepareStatement(SQL_FIND_CLASS_BY_ID);
            state.setInt(1,id);
            //3.    执行SQL语句 , 并获取结果集
            result = state.executeQuery();
            //4.    遍历结果集, 将结果集中的每一行数据 封装为一个GoodsClass对象
            if(result.next()){
                int parentId = result.getInt("parentId");
                String className = result.getString("className");
                //将结果集中的每一行数据 封装为一个GoodsClass对象
                GoodsClass goodsClass = new GoodsClass(id,parentId,className);
                return goodsClass;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6.    释放
            DruidUtil.close(conn,state,result);
        }
        return null;
    }
}
