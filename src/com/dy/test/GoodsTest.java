package com.dy.test;

import com.dy.bean.Carts;
import com.dy.bean.Goods;
import com.dy.dao.BaseGoodsDao;
import com.dy.dao.GoodsDaoImp;

import java.util.ArrayList;
import java.util.List;

public class GoodsTest {
    public static void main(String[] args) {
        BaseGoodsDao dao = new GoodsDaoImp();
        //List<Goods> data = dao.findAll();
        //List<Goods> data = dao.findGoodsByClass1(1, 10, 10, 1);
        //List<Goods> data = dao.findGoodsByClass2(101, 10, 10, 1);
        //List<Goods> data = dao.findGoodsLikeName("红色椅子",0,10,0);
        //Goods data = dao.findGoodsById(161);
        List<Carts> cs = new ArrayList<>();
        Carts c = new Carts();
        c.setGoodsId(1);
        cs.add(c);
        Carts c2 = new Carts();
        c2.setGoodsId(2);
        cs.add(c2);


        List<Goods> data = dao.findGoodsByCarts(cs);
        System.out.println(data);
    }
}
