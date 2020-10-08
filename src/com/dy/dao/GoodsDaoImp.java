package com.dy.dao;

import com.dy.bean.Carts;
import com.dy.bean.Goods;
import com.dy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  尽可能晚的建立数据库连接, 尽可能早的释放数据库连接

 *
 */
public class GoodsDaoImp implements BaseGoodsDao {
    private static final String SQL_FIND_ALL_A = "SELECT * FROM JJ_GOODS ORDER BY PRICE";
    private static final String SQL_FIND_ALL_D = "SELECT * FROM JJ_GOODS ORDER BY PRICE DESC";
    /**
     * 按照一级分类查询 价格正序
     */
    private static final String SQL_FIND_BY_CLASS1_A = "SELECT * FROM JJ_GOODS WHERE CLASSID1=? ORDER BY PRICE limit ?,? ";
    /**
     * 按照一级分类查询 价格倒序
     */
    private static final String SQL_FIND_BY_CLASS1_D = "SELECT * FROM JJ_GOODS WHERE CLASSID1=? ORDER BY PRICE desc limit ?,?";
    /**
     * 按照二级分类查询 价格正序
     */
    private static final String SQL_FIND_BY_CLASS2_A = "SELECT * FROM JJ_GOODS WHERE CLASSID2=? ORDER BY PRICE limit ?,? ";
    /**
     * 按照二级分类查询 价格倒序
     */
    private static final String SQL_FIND_BY_CLASS2_D = "SELECT * FROM JJ_GOODS WHERE CLASSID2=? ORDER BY PRICE desc limit ?,?";

    private static final String SQL_FIND_LIKE_NAME_A = "SELECT * FROM JJ_GOODS WHERE NAME LIKE ? ORDER BY PRICE limit ?,? ";
    private static final String SQL_FIND_LIKE_NAME_D = "SELECT * FROM JJ_GOODS WHERE NAME LIKE ? ORDER BY PRICE desc limit ?,?";
    private static final String SQL_FIND_BY_ID ="SELECT * FROM JJ_GOODS WHERE ID=?";

    @Override
    public List<Goods> findGoodsByCarts(List<Carts> cs) {
        //0.    创建一个List集合 , 用于存储查询的结果
        List<Goods> data = new ArrayList<>();
        Connection conn = null;
        Statement state = null;
        ResultSet result = null;
        try {
            //1.    从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
            state = conn.createStatement();
            String para = "";
            for(int i=0;i<cs.size();i++){
                para+=cs.get(i).getGoodsId();
                if(i != cs.size()-1){
                    //不最后一个商品
                    para+=",";
                }
            }
            String sql = "select id,name,imgs,price from jj_goods where id in("+para+");";
            System.out.println(sql);
            //3.    执行查询语句 , 并得到结果集
            result = state.executeQuery(sql);
            //4.    遍历结果集
            while(result.next()){
                //5.    取出结果集中每一行数据, 将其封装为一个个的商品对象
                int id = result.getInt("id");
                String name = result.getString("name");
                String imgs = result.getString("imgs");
                double price = result.getDouble("price");
                Goods g = new Goods();
                g.setId(id);
                g.setName(name);
                g.setImgs(imgs);
                g.setPrice(price);
                //6.    将商品对象 存储到第0步创建的集合中
                data.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        //7.    释放数据库资源
        return data;
    }

    @Override
    public List<Goods> findAll(int orderBy) {
        //0.    创建一个List集合 , 用于存储查询的结果
        List<Goods> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
            if(orderBy == 0){
                state = conn.prepareStatement(SQL_FIND_ALL_A);
            }else{
                state = conn.prepareStatement(SQL_FIND_ALL_D);
            }
            //3.    执行查询语句 , 并得到结果集
            result = state.executeQuery();
            //4.    遍历结果集
            while(result.next()){
                //5.    取出结果集中每一行数据, 将其封装为一个个的商品对象
                int id = result.getInt("id");
                String name = result.getString("name");
                String imgs = result.getString("imgs");
                double price = result.getDouble("price");
                String gdesc = result.getString("gdesc");
                int stock = result.getInt("stock");
                int appraiseNum = result.getInt("appraiseNum");
                String info = result.getString("info");
                Date createTime = result.getDate("createTime");
                int classid1 = result.getInt("classid1");
                int classid2 = result.getInt("classid2");

                Goods g = new Goods( id,  name,  imgs,  price,  gdesc,  stock, appraiseNum, info,
                createTime,  classid1,  classid2);
                //6.    将商品对象 存储到第0步创建的集合中
                data.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        //7.    释放数据库资源
        return data;
    }

    /**
     * 根据一级分类查询商品
     *
     * @param class1Id 一级分类编号
     * @param page     分页查询的页数
     * @param size     每页显示的数据数量
     * @param orderBy  排序方式
     * @return 查询结果
     */
    @Override
    public List<Goods> findGoodsByClass1(int class1Id, int page, int size, int orderBy) {
        //0.    创建一个List集合 , 用于存储查询的结果
        List<Goods> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
            if(orderBy == 0){
                state = conn.prepareStatement(SQL_FIND_BY_CLASS1_A);
            }else{
                state = conn.prepareStatement(SQL_FIND_BY_CLASS1_D);
            }
            state.setInt(1,class1Id);
            state.setInt(2,page);
            state.setInt(3,size);
            //3.    执行查询语句 , 并得到结果集
            result = state.executeQuery();
            //4.    遍历结果集
            while(result.next()){
                //5.    取出结果集中每一行数据, 将其封装为一个个的商品对象
                int id = result.getInt("id");
                String name = result.getString("name");
                String imgs = result.getString("imgs");
                double price = result.getDouble("price");
                String gdesc = result.getString("gdesc");
                int stock = result.getInt("stock");
                int appraiseNum = result.getInt("appraiseNum");
                String info = result.getString("info");
                Date createTime = result.getDate("createTime");
                int classid1 = result.getInt("classid1");
                int classid2 = result.getInt("classid2");

                Goods g = new Goods( id,  name,  imgs,  price,  gdesc,  stock, appraiseNum, info,
                        createTime,  classid1,  classid2);
                //6.    将商品对象 存储到第0步创建的集合中
                data.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        //7.    释放数据库资源
        return data;
    }

    /**
     * 根据二级分类查询商品
     *
     * @param class2Id 二级分类编号
     * @param page     分页查询的页数
     * @param size     每页显示的数据数量
     * @param orderBy  0正序 1倒序
     * @return 查询结果
     */
    @Override
    public List<Goods> findGoodsByClass2(int class2Id, int page, int size, int orderBy) {
        //0.    创建一个List集合 , 用于存储查询的结果
        List<Goods> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
            if(orderBy == 0){
                state = conn.prepareStatement(SQL_FIND_BY_CLASS2_A);
            }else{
                state = conn.prepareStatement(SQL_FIND_BY_CLASS2_D);
            }
            state.setInt(1,class2Id);
            state.setInt(2,page);
            state.setInt(3,size);
            //3.    执行查询语句 , 并得到结果集
            result = state.executeQuery();
            //4.    遍历结果集
            while(result.next()){
                //5.    取出结果集中每一行数据, 将其封装为一个个的商品对象
                int id = result.getInt("id");
                String name = result.getString("name");
                String imgs = result.getString("imgs");
                double price = result.getDouble("price");
                String gdesc = result.getString("gdesc");
                int stock = result.getInt("stock");
                int appraiseNum = result.getInt("appraiseNum");
                String info = result.getString("info");
                Date createTime = result.getDate("createTime");
                int classid1 = result.getInt("classid1");
                int classid2 = result.getInt("classid2");

                Goods g = new Goods( id,  name,  imgs,  price,  gdesc,  stock, appraiseNum, info,
                        createTime,  classid1,  classid2);
                //6.    将商品对象 存储到第0步创建的集合中
                data.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        //7.    释放数据库资源
        return data;
    }

    /**
     * 根据用户输入的商品名称, 模糊查询商品
     *
     * @param name    二级分类编号
     * @param page    分页查询的页数
     * @param size    每页显示的数据数量
     * @param orderBy 0正序 1倒序
     * @return 查询结果
     */
    @Override
    public List<Goods> findGoodsLikeName(String name, int page, int size, int orderBy) {
        //0.    创建一个List集合 , 用于存储查询的结果
        List<Goods> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
            if(orderBy == 0){
                state = conn.prepareStatement(SQL_FIND_LIKE_NAME_A);
            }else{
                state = conn.prepareStatement(SQL_FIND_LIKE_NAME_D);
                
            }
            state.setString(1,"%"+name+"%");
            state.setInt(2,page);
            state.setInt(3,size);
            //3.    执行查询语句 , 并得到结果集
            result = state.executeQuery();
            //4.    遍历结果集
            while(result.next()){
                //5.    取出结果集中每一行数据, 将其封装为一个个的商品对象
                int id = result.getInt("id");
                String name2 = result.getString("name");
                String imgs = result.getString("imgs");
                double price = result.getDouble("price");
                String gdesc = result.getString("gdesc");
                int stock = result.getInt("stock");
                int appraiseNum = result.getInt("appraiseNum");
                String info = result.getString("info");
                Date createTime = result.getDate("createTime");
                int classid1 = result.getInt("classid1");
                int classid2 = result.getInt("classid2");

                Goods g = new Goods( id,  name2,  imgs,  price,  gdesc,  stock, appraiseNum, info,
                        createTime,  classid1,  classid2);
                //6.    将商品对象 存储到第0步创建的集合中
                data.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        //7.    释放数据库资源
        return data;
    }

    /**
     * 根据商品编号查询单个商品详情
     *
     * @param id 要查询的商品编号
     * @return 查询的结果
     */
    @Override
    public Goods findGoodsById(int id) {

        Connection conn = null;
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            //1.    从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
             state = conn.prepareStatement(SQL_FIND_BY_ID);
            state.setInt(1,id);
            //3.    执行查询语句 , 并得到结果集
            result = state.executeQuery();
            //4.    遍历结果集
            if(result.next()){
                //5.    取出结果集中每一行数据, 将其封装为一个个的商品对象
                String name2 = result.getString("name");
                String imgs = result.getString("imgs");
                double price = result.getDouble("price");
                String gdesc = result.getString("gdesc");
                int stock = result.getInt("stock");
                int appraiseNum = result.getInt("appraiseNum");
                String info = result.getString("info");
                Date createTime = result.getDate("createTime");
                int classid1 = result.getInt("classid1");
                int classid2 = result.getInt("classid2");

                Goods g = new Goods( id,  name2,  imgs,  price,  gdesc,  stock, appraiseNum, info,
                        createTime,  classid1,  classid2);
                //6.    将商品对象 返回给调用者
                return g;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        //7.    释放数据库资源
        return null;
    }

}
