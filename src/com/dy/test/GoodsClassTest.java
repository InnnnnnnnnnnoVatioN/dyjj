package com.dy.test;

import com.dy.bean.GoodsClass;
import com.dy.dao.BaseGoodsClassDao;
import com.dy.dao.GoodsClassDaoImp;

import java.util.List;

public class GoodsClassTest {
    public static void main(String[] args) {
        BaseGoodsClassDao dao = new GoodsClassDaoImp();
        List<GoodsClass> data = dao.findAll();
        System.out.println(data);
    }
}
