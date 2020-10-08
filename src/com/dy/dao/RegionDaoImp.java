package com.dy.dao;

import com.dy.bean.CnRegion;
import com.dy.dao.BaseRegionDao;
import com.dy.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDaoImp implements BaseRegionDao {
    private static final String SQL_FIND_BY_LEVEL = "SELECT * FROM CN_REGION WHERE LEVEL=?";
    private static final String SQL_FIND_BY_CODE = "SELECT * FROM CN_REGION WHERE CODE=?";
    private static final String SQL_FIND_BY_PARENTCODE = "SELECT * FROM CN_REGION WHERE SUPERIOR_CODE=?";

    /**
     * 根据城市级别， 查询城市列表
     *
     * @param level
     * @return
     */
    @Override
    public List<CnRegion> findByLevel(int level) {
        //0.    创建一个空的集合， 用于存储查询的结果
        ArrayList<CnRegion> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    获取连接对象
            conn = DruidUtil.getConnection();
            //2.    通过链接对象， 获取执行环境
            state = conn.prepareStatement(SQL_FIND_BY_LEVEL);
            state.setInt(1,level);
            //3.    执行SQL语句 ， 并得到结果集
            result = state.executeQuery();
            //4.    遍历结果集
            while(result.next()){
                int id = result.getInt("id");
                String code = result.getString("code");
                String name = result.getString("name");
                //5.    将查询结果， 封装为城市对象， 并存储到集合中
                CnRegion r = new CnRegion();
                r.setId(id);
                r.setCode(code);
                r.setName(name);
                data.add(r);
            }
            //5.    返回查询的结果
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }

    /**
     * 根据城市码 ， 查询单个城市
     *
     * @param code 查询的城市码
     * @return
     */
    @Override
    public CnRegion findByCode(String code) {
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    获取连接对象
            conn = DruidUtil.getConnection();
            //2.    通过链接对象， 获取执行环境
            state = conn.prepareStatement(SQL_FIND_BY_CODE);
            state.setString(1,code);
            //3.    执行SQL语句 ， 并得到结果集
            result = state.executeQuery();
            //4.    遍历结果集
            if(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String parentCode = result.getString("SUPERIOR_CODE");
                String lng = result.getString("lng");
                String lat = result.getString("lat");
                int level = result.getInt("level");
                //5.    将查询结果， 封装为城市对象
                CnRegion r = new CnRegion();
                r.setId(id);
                r.setCode(code);
                r.setName(name);
                r.setParentCode(parentCode);
                r.setLng(lng);
                r.setLat(lat);
                r.setLevel(level);
                //5.    返回查询的结果
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }

    /**
     * 根据城市码 ， 查询所有的子城市
     *
     * @param code 城市码
     * @return
     */
    @Override
    public List<CnRegion> findByParentCode(String parentCode) {
        //0.    创建一个空的集合， 用于存储查询的结果
        ArrayList<CnRegion> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    获取连接对象
            conn = DruidUtil.getConnection();
            //2.    通过链接对象， 获取执行环境
            state = conn.prepareStatement(SQL_FIND_BY_PARENTCODE);
            state.setString(1,parentCode);
            //3.    执行SQL语句 ， 并得到结果集
            result = state.executeQuery();
            //4.    遍历结果集
            while(result.next()){
                int id = result.getInt("id");
                String code = result.getString("code");
                String name = result.getString("name");
                //5.    将查询结果， 封装为城市对象， 并存储到集合中
                CnRegion r = new CnRegion();
                r.setId(id);
                r.setCode(code);
                r.setName(name);
                data.add(r);
            }
            //5.    返回查询的结果
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }
}
