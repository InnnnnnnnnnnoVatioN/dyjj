package com.dy.test;

import com.dy.dao.BaseCartsDao;
import com.dy.dao.CartsDaoImp;

public class CartTest {
    public static void main(String[] args) {
        BaseCartsDao dao = new CartsDaoImp();
        //dao.addCart(2,1);
        //dao.updateCartNum(2,1,-1);
        dao.cartCheck(2,1,1);
    }
}
