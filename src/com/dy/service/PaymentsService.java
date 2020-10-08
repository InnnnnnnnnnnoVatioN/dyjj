package com.dy.service;

import com.dy.bean.Payments;
import com.dy.dao.BasePaymentsDao;
import com.dy.dao.PaymentsDaoImp;

import java.util.List;

public class PaymentsService {
    private static BasePaymentsDao dao = new PaymentsDaoImp();


    /**
     * 查询所有支付方式
     * @return
     */
    public static List<Payments> findAll(){
        return dao.findAll();
    }

    /**
     * 根据id查询单个支付方式
     * @param id
     * @return
     */
    public static Payments findById(int id){
        return dao.findById(id);
    }

}
