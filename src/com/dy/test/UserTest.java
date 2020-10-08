package com.dy.test;

import com.dy.bean.User;
import com.dy.dao.BaseUserDao;
import com.dy.dao.UserDaoImp;

public class UserTest {

    public static void main(String[] args) {
        BaseUserDao dao = new UserDaoImp();
        //boolean reg = dao.reg(new User("18516955565", "123456789"));
        //User u = new User("18516955565", "123456789");
        //u.setLastIp("123.12.123.12");
        //boolean login = dao.login(u);
        boolean flag = dao.updatePassword(new User("18516955565", "123456789"), "123456");
        System.out.println(flag);
    }




}
