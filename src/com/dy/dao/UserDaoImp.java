package com.dy.dao;

import com.dy.bean.User;
import com.dy.util.DruidUtil;

import java.sql.*;

public class UserDaoImp implements BaseUserDao {
    private static final String SQL_INSERT = "INSERT INTO JJ_USERS(USERPHONE,PASSWORD,CREATETIME) VALUES(?,?,NOW())";
    private static final String SQL_SELECT_BY_PASSWORD = "SELECT * FROM JJ_USERS WHERE USERPHONE=? AND PASSWORD=?";
    private static final String SQL_LOGIN_SUCCESS_UPDATE = "UPDATE JJ_USERS SET LASTIP=?,LASTTIME=? WHERE USERPHONE=?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE JJ_USERS SET PASSWORD=? WHERE USERPHONE=? AND PASSWORD=?";

    /**
     * 注册用户
     *
     * @param u 要注册的用户信息, 包含帐号 密码
     * @return 注册结果 true表示成功
     */
    @Override
    public boolean reg(User u) {
        Connection conn = null;
        PreparedStatement state = null;
        try {
            //1.    从连接池中获取连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
            state = conn.prepareStatement(SQL_INSERT);
            state.setString(1,u.getUserPhone());
            state.setString(2,u.getPassword());
            //3.    执行SQL语句
            int row = state.executeUpdate();
            //4.    根据执行的结果, 返回不同的值
            return row>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.    释放资源
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * 用户登录
     *
     * @param u 要登陆的用户信息, 包含帐号和密码
     *          当登录成功时, 此对象中其它属性值 会 自动赋值为登录用户的信息.
     * @return 登录的结果 true表示成功
     */
    @Override
    public boolean login(User u) {
        Connection conn = null;
        PreparedStatement state = null;
        try {
            conn = DruidUtil.getConnection();
            state = conn.prepareStatement(SQL_SELECT_BY_PASSWORD);
            state.setString(1,u.getUserPhone());
            state.setString(2,u.getPassword());
            //4.    执行SQL语句, 并得到结果集
            ResultSet result = state.executeQuery();
            if(result.next()){
                //登录成功
                //1.    修改最后登录时间和ip
                loginSuccessUpdate(u.getLastIp(),u.getUserPhone());
                //2.    取出用户其他信息, 存储到对象u中
                u.setCreateTime(result.getDate("createTime"));
                u.setId(result.getInt("id"));
                u.setEmail(result.getString("email"));
                //u.setLastIp(result.getString("lastIp"));
                //u.setLastTime(result.getDate("lastTime"));
                u.setNickName(result.getString("nickname"));
                u.setUserPhoto(result.getString("userphoto"));
                return true;
            }

           /* else{
                //登录失败
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.    释放资源
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * 用于登录成功时, 修改用户表格中的 最后登录时间 和 最后登录ip
     * @param ip
     */
    private void loginSuccessUpdate(String ip,String userPhone){
        Connection conn = null;
        PreparedStatement state = null;
        try {
            //1.    从连接池中获取连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
            state = conn.prepareStatement(SQL_LOGIN_SUCCESS_UPDATE);
            state.setString(1,ip);
            state.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
            state.setString(3,userPhone);
            //3.    执行SQL语句
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.    释放资源
            DruidUtil.close(conn,state,null);
        }
    }


    /**
     * 用户密码修改
     *
     * @param u       用户原信息
     * @param newPass 用户新密码
     * @return 修改的结果 true表示成功
     */
    @Override
    public boolean updatePassword(User u, String newPass) {
        Connection conn = null;
        PreparedStatement state = null;
        try {
            //1.    从连接池中获取连接
            conn = DruidUtil.getConnection();
            //2.    预编译SQL语句 , 并填充参数
            state = conn.prepareStatement(SQL_UPDATE_PASSWORD);
            state.setString(1,newPass);
            state.setString(2,u.getUserPhone());
            state.setString(3,u.getPassword());
            //3.    执行SQL语句
            int row = state.executeUpdate();
            return row>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.    释放资源
            DruidUtil.close(conn,state,null);
        }
        return false;
    }
}
