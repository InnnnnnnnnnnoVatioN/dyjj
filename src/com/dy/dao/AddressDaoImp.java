package com.dy.dao;

import com.dy.bean.Address;
import com.dy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImp implements BaseAddressDao{
    private static final String SQL_FIND_BY_USERID = "SELECT * FROM JJ_ADDRESS WHERE USERID=? ORDER BY ISDEFAULT DESC";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM JJ_ADDRESS WHERE ID=?";
    private static final String SQL_INSERT = "INSERT INTO JJ_ADDRESS(userId,userName,userPhone,provinceId,cityId,areaId,streetId,userAddress,isDefault,createTime)" +
            " VALUES(?,?,?,?,?,?,?,?,?,now())";
    private static final String SQL_UPDATE_BY_ID = "UPDATE JJ_ADDRESS SET userName=?,userPhone=?,provinceId=?,cityId=?,areaId=?,streetId=?,userAddress=? WHERE ID=? ";
    private static final String SQL_UPDATE_DEFAULT_BY_ID = "UPDATE JJ_ADDRESS SET isDefault=1 where id=";
    private static final String SQL_UPDATE_DEFAULT_BY_USERID = "UPDATE JJ_ADDRESS SET isDefault=0 where userid=";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM JJ_ADDRESS WHERE ID=?";

    /**
     * 用于查询用户的所有收货地址
     *
     * @param userId 用户的id
     * @return 查询的结果
     */
    @Override
    public List<Address> findByUserId(int userId) {
        //0.    创建一个集合, 用于存储查询的结果
        ArrayList<Address> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    获取连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL
            state = conn.prepareStatement(SQL_FIND_BY_USERID);
            //3.    填充预编译的参数
            state.setInt(1,userId);
            //4.    执行并得到结果集
            result = state.executeQuery();
            //5.    遍历结果集,
            while(result.next()){
                //并将结果集中每一行数据 组装为对象
                int id = result.getInt("id");
                String userName = result.getString("userName");
                String userPhone = result.getString("userPhone");
                int provinceId = result.getInt("provinceId");
                int cityId = result.getInt("cityId");
                int areaId = result.getInt("areaId");
                int streetId = result.getInt("streetId");
                String userAddress = result.getString("userAddress");
                int isDefault = result.getInt("isDefault");
                Timestamp createTime = result.getTimestamp("createTime");
                Address address = new Address(id,userId,userName,userPhone,provinceId,cityId,areaId,streetId,userAddress,isDefault,createTime);
                //6.    将组装好的对象 存储到集合中
                data.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 通过id 查询某个收货地址信息
     *
     * @param id 地址id
     * @return 查询结果, 查询失败返回null
     */
    @Override
    public Address findById(int id) {

        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    获取连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL
            state = conn.prepareStatement(SQL_FIND_BY_ID);
            //3.    填充预编译的参数
            state.setInt(1,id);
            //4.    执行并得到结果集
            result = state.executeQuery();
            //5.    遍历结果集,
            if(result.next()){
                //并将结果集中每一行数据 组装为对象
                int userId = result.getInt("userId");
                String userName = result.getString("userName");
                String userPhone = result.getString("userPhone");
                int provinceId = result.getInt("provinceId");
                int cityId = result.getInt("cityId");
                int areaId = result.getInt("areaId");
                int streetId = result.getInt("streetId");
                String userAddress = result.getString("userAddress");
                int isDefault = result.getInt("isDefault");
                Timestamp createTime = result.getTimestamp("createTime");
                Address address = new Address(id,userId,userName,userPhone,provinceId,cityId,areaId,streetId,userAddress,isDefault,createTime);
                //6.    将组装好的对象 存储到集合中
                return address;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }

    /**
     * 用于地址信息的插入
     *
     * @param address 地址对象
     * @return 插入结果
     */
    @Override
    public boolean insert(Address address) {
        //1.    获取连接对象
        Connection conn = DruidUtil.getConnection();
        //2.    预编译SQL语句
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //参数2: 表示的是, 本次执行需要返回 自增列.
            state = conn.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            //3.    参数的填充
            state.setInt(1,address.getUserId());
            state.setString(2,address.getUserName());
            state.setString(3,address.getUserPhone());
            state.setInt(4,address.getProvinceId());
            state.setInt(5,address.getCityId());
            state.setInt(6,address.getAreaId());
            state.setInt(7,address.getStreetId());
            state.setString(8,address.getUserAddress());
            state.setInt(9,address.getIsDefault());
            //state.setTimestamp(10,address.getCreateTime());
            //4.    执行SQL DML语句
            state.executeUpdate();
            //5.    执行查询语句 (查询当前连接最新插入的id值)
            //作用: 用于获取最新插入的自增的字段值
            result = state.getGeneratedKeys();
            // SELECT LAST_INSERT_ID():基于连接的
            // SELECT @@IDENTITY;基于数据库全局的
            if(result.next()){
                int id = result.getInt(1);
                address.setId(id);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6.    释放连接资源
            DruidUtil.close(conn,state,result);
        }
        //userId,userName,userPhone,provinceId,cityId,areaId,streetId,userAddress,isDefault,createTime
        return false;
    }

    /**
     * 用于地址信息的修改
     *
     * @param id         要修改的id
     * @param newAddress 新的地址信息
     * @return
     */
    @Override
    public boolean updateById(int id, Address newAddress) {
        //1.    获取连接对象
        Connection conn = DruidUtil.getConnection();
        //2.    预编译SQL语句
        PreparedStatement state = null;
        try {
            //参数2: 表示的是, 本次执行需要返回 自增列.
            state = conn.prepareStatement(SQL_UPDATE_BY_ID);
            //3.    参数的填充userName=?,userPhone=?,provinceId=?,cityId=?,areaId=?,streetId=?,userAddress=? WHERE ID=?
            state.setString(1,newAddress.getUserName());
            state.setString(2,newAddress.getUserPhone());
            state.setInt(3,newAddress.getProvinceId());
            state.setInt(4,newAddress.getCityId());
            state.setInt(5,newAddress.getAreaId());
            state.setInt(6,newAddress.getStreetId());
            state.setString(7,newAddress.getUserAddress());
            state.setInt(8,id);
            //4.    执行SQL DML语句
            return state.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6.    释放连接资源
            DruidUtil.close(conn,state,null);
        }
        //userId,userName,userPhone,provinceId,cityId,areaId,streetId,userAddress,isDefault,createTime
        return false;
    }

    /**
     * 根据id ,修改默认地址
     *
     * @param id 要修改为默认地址的id
     * @return 修改的结果
     */
    @Override
    public boolean updateDefaultById(int id,int userId) {
        //1.    获取连接对象
        Connection conn = DruidUtil.getConnection();
        //2.    SQL语句
        Statement state = null;
        try {
           state = conn.createStatement();
           //将用户所有的收获地址, 取消了默认
           state.addBatch(SQL_UPDATE_DEFAULT_BY_USERID+userId);
            //根据ID, 将某一个收获地址, 更改为默认.
           state.addBatch(SQL_UPDATE_DEFAULT_BY_ID+id);
           state.executeBatch();
           return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6.    释放连接资源
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * 用于用户地址的删除
     *
     * @param id 要删除的id
     * @return
     */
    @Override
    public boolean deleteById(int id) {
        //1.    获取连接对象
        Connection conn = DruidUtil.getConnection();
        //2.    预编译SQL语句
        PreparedStatement state = null;
        try {
            //参数2: 表示的是, 本次执行需要返回 自增列.
            state = conn.prepareStatement(SQL_DELETE_BY_ID);
            //3.    参数的填充userName=?,userPhone=?,provinceId=?,cityId=?,areaId=?,streetId=?,userAddress=? WHERE ID=?
            state.setInt(1,id);
            //4.    执行SQL DML语句
            return state.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6.    释放连接资源
            DruidUtil.close(conn,state,null);
        }
        return false;
    }
}
