package com.dy.service;

import com.dy.bean.User;
import com.dy.dao.BaseUserDao;
import com.dy.dao.UserDaoImp;

public class UserService {
    private static BaseUserDao dao = new UserDaoImp();

    /**
     * 注册用户
     * @param u 要注册的用户信息, 包含帐号 密码
     * @return 注册结果 true表示成功
     */
    public static boolean reg(User u){
        return dao.reg(u);
    }

    /**
     * 用户登录
     * @param u 要登陆的用户信息, 包含帐号和密码
     *          当登录成功时, 此对象中其它属性值 会 自动赋值为登录用户的信息.
     * @return 登录的结果 true表示成功
     */
    public static boolean login(User u){
        return dao.login(u);
    }

    /**
     * 用户密码修改
     * @param u 用户原信息
     * @param newPass 用户新密码
     * @return 修改的结果 true表示成功
     */
    public static boolean updatePassword(User u,String newPass){
        return dao.updatePassword(u,newPass);
    }



}
