package com.dy.dao;

import com.dy.bean.Carts;
import com.dy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartsDaoImp implements BaseCartsDao{
    private static final String SQL_FIND_BY_USERID = "SELECT * FROM JJ_CARTS WHERE USERID=?";
    private static final String SQL_FIND_BY_USERID_AND_CHECK = "SELECT * FROM JJ_CARTS WHERE USERID=? AND ISCHECK=?";
    private static final String SQL_FIND_BY_USERID_AND_GOODSID = "SELECT ID FROM JJ_CARTS WHERE USERID=? AND GOODSID=?";
    private static final String SQL_ADD_CART_UPDATE_CARTNUM = "UPDATE JJ_CARTS SET CARTNUM=CARTNUM+1 WHERE USERID=? AND GOODSID=?";
    private static final String SQL_ADD_CART_INSERT_CART = "INSERT INTO JJ_CARTS(USERID,ISCHECK,GOODSID,CARTNUM) VALUES(?,0,?,1)";
    private static final String SQL_UPDATE_CARTNUM = "UPDATE JJ_CARTS SET CARTNUM=CARTNUM+? WHERE USERID=? AND GOODSID=?";
    private static final String SQL_UPDATE_CHECK = "UPDATE JJ_CARTS SET ISCHECK=? WHERE USERID=? AND GOODSID=?";
    private static final String SQL_DELETE_CART = "DELETE FROM JJ_CARTS WHERE USERID=? AND GOODSID=?";
    private static final String SQL_DELETE_BY_CHECK = "DELETE FROM JJ_CARTS WHERE USERID=? AND ISCHECK=?";


    /**
     * 通过用户的编号 , 查询用户所有的购物车
     * @param userId 用户的编号
     * @return 购物车集合
     */
    @Override
    public List<Carts> findByUserId(int userId) {
        //0.    创建一个集合, 用于存储查询的结果
        ArrayList<Carts> cs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    从德鲁伊连接池中 获取数据库连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句
            state = conn.prepareStatement(SQL_FIND_BY_USERID);
            //3.    填充参数
            state.setInt(1,userId);
            //4.    执行 , 并得到结果集
            result = state.executeQuery();
            //5.    遍历结果集, 取出结果集中每一行数据, 将其组装为购物车对象 并存储到集合中
            while(result.next()){
                int id = result.getInt("id");
                int goodsId = result.getInt("goodsId");
                int isCheck = result.getInt("isCheck");
                int cartNum = result.getInt("cartNum");
                Carts e = new Carts(id,userId,goodsId,isCheck,cartNum);
                cs.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return cs;
    }

    /**
     * 通过用户的编号 , 查询用户所有的购物车
     *
     * @param userId  用户的编号
     * @param isCheck 0选中 1未选中
     * @return 购物车集合
     */
    @Override
    public List<Carts> findByUserId(int userId, int isCheck) {
        //0.    创建一个集合, 用于存储查询的结果
        ArrayList<Carts> cs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    从德鲁伊连接池中 获取数据库连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句
            state = conn.prepareStatement(SQL_FIND_BY_USERID_AND_CHECK);
            //3.    填充参数
            state.setInt(1,userId);
            state.setInt(2,isCheck);
            //4.    执行 , 并得到结果集
            result = state.executeQuery();
            //5.    遍历结果集, 取出结果集中每一行数据, 将其组装为购物车对象 并存储到集合中
            while(result.next()){
                int id = result.getInt("id");
                int goodsId = result.getInt("goodsId");
                int cartNum = result.getInt("cartNum");
                Carts e = new Carts(id,userId,goodsId,isCheck,cartNum);
                cs.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return cs;
    }

    /**
     * 用于向carts表格中添加一行数据
     *
     * @param userId  用户id
     * @param goodsId 商品id
     */
    @Override
    public void addCart(int userId, int goodsId) {

        //判断在购物车中 此用户是否存在此是商品
        boolean flag = findByUserIdAndGoodsId(userId, goodsId);
        Connection conn = null;
        PreparedStatement state = null;
        try {
            //1.    从连接池中 获取连接对象
            conn = DruidUtil.getConnection();
            //2.    根据判断的结果 , 预编译不同的SQL语句
            if(flag){
                //数量的增加
                state = conn.prepareStatement(SQL_ADD_CART_UPDATE_CARTNUM);
            }else{
                //表格行的插入
                state = conn.prepareStatement(SQL_ADD_CART_INSERT_CART);
            }
            //3.    填充参数
            state.setInt(1,userId);
            state.setInt(2,goodsId);
            //4.    执行DML语句
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
    }

    /**
     * 查询某个用户的购物车中是否拥有某个商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 查询的结果, true表示存在
     */
    private boolean findByUserIdAndGoodsId(int userId, int goodsId){

        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    从连接池中 获取连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句
            state = conn.prepareStatement(SQL_FIND_BY_USERID_AND_GOODSID);
            //3.    填充参数
            state.setInt(1,userId);
            state.setInt(2,goodsId);
            result = state.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return false;
    }


    /**
     * 用于修改购物车的商品数量
     *
     * @param userId  用户id
     * @param goodsId 商品id
     * @param num     增加或减少数量
     * @return 修改的结果, 200表示成功 -
     */
    @Override
    public int updateCartNum(int userId, int goodsId, int num) {
        //1.    获取数据库的连接
        Connection conn = null;
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_UPDATE_CARTNUM);
            state.setInt(1,num);
            state.setInt(2,userId);
            state.setInt(3,goodsId);
            return state.executeUpdate()>0?200:-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return -1;
    }

    /**
     * 用于选中 或 取消选中购物车
     *
     * @param userId  选中或取消选中的商品id
     * @param goodsId 选中或取消选中的商品id
     * @param isCheck 选中0  或 取消选中1
     * @return 处理的结果 ,200成功  -1表示失败
     */
    @Override
    public int cartCheck(int userId, int goodsId, int isCheck) {
        //1.    获取数据库的连接
        Connection conn = null;
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_UPDATE_CHECK);
            state.setInt(1,isCheck);
            state.setInt(2,userId);
            state.setInt(3,goodsId);
            return state.executeUpdate()>0?200:-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return -1;
    }

    /**
     * 用于从用户的购物车中 删除一个商品
     *
     * @param userId  用户id
     * @param goodsId 商品id
     * @return 删除的结果 200表示成功 -1表示失败
     */
    @Override
    public int cartDel(int userId, int goodsId) {
        //1.    获取数据库的连接
        Connection conn = null;
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_DELETE_CART);
            state.setInt(1,userId);
            state.setInt(2,goodsId);
            return state.executeUpdate()>0?200:-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return -1;
    }

    /**
     * 用于从用户的购物车中 删除一个商品
     *
     * @param userId  用户id
     * @param isCheck 0删除选中 1删除未选中
     * @return 删除的结果 200表示成功 -1表示失败
     */
    @Override
    public int cartDelByCheck(int userId, int isCheck) {
        //1.    获取数据库的连接
        Connection conn = null;
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_DELETE_BY_CHECK);
            state.setInt(1,userId);
            state.setInt(2,isCheck);
            return state.executeUpdate()>0?200:-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return -1;
    }

    /**
     * 用于向数据库中, 插入session中的购物车数据
     *
     * @param userId 用户id
     * @param data   session中的购物车集合
     * @return 插入结果
     */
    @Override
    public int insertSessionCarts(int userId, List<Carts> data) {

        Connection conn = null;

        Statement state = null;
        try {
            //1.    获取数据库连接
            conn = DruidUtil.getConnection();
            //2.    创建执行环境
            state = conn.createStatement();
            //3.    向执行环境中 添加一个SQL语句 到批处理中
            for(Carts c:data){
                String sql = null;
                if(findByUserIdAndGoodsId(userId,c.getGoodsId())){
                    sql = "UPDATE JJ_CARTS SET CARTNUM=CARTNUM+"+c.getCartNum()+",ISCHECK="+c.getIsCheck()+" WHERE USERID="+userId+" AND GOODSID="+c.getGoodsId();
                }else{
                    sql = "INSERT INTO JJ_CARTS(USERID,ISCHECK,GOODSID,CARTNUM) VALUES("+userId+","+c.getIsCheck()+","+c.getGoodsId()+","+c.getCartNum()+")";
                }
                state.addBatch(sql);
            }
            //4.    执行批处理
            state.executeBatch();
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.    释放资源
            DruidUtil.close(conn,state,null);
        }
        return -1;

    }
}
