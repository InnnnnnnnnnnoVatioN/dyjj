package com.dy.dao;

import com.dy.bean.User;

public interface BaseUserDao {

    /**
     * 注册用户
     * @param u 要注册的用户信息, 包含帐号 密码
     * @return 注册结果 true表示成功
     */
    boolean reg(User u);

    /**
     * 用户登录
     * @param u 要登陆的用户信息, 包含帐号和密码
     *          当登录成功时, 此对象中其它属性值 会 自动赋值为登录用户的信息.
     * @return 登录的结果 true表示成功
     */
    boolean login(User u);

    /**
     * 用户密码修改
     * @param u 用户原信息
     * @param newPass 用户新密码
     * @return 修改的结果 true表示成功
     */
    boolean updatePassword(User u,String newPass);


}
