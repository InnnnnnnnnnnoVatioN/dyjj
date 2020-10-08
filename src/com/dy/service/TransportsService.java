package com.dy.service;

import com.dy.bean.Transports;
import com.dy.dao.BaseTransportsDao;
import com.dy.dao.TransportsDaoImp;

import java.util.List;

public class TransportsService {
    private static BaseTransportsDao dao = new TransportsDaoImp();


    /**
     * 查询所有配送方式
     * @return
     */
    public static List<Transports> findAll(){
        return dao.findAll();
    }

    /**
     * 根据id查询单个配送方式
     * @param id
     * @return
     */
    public static Transports findById(int id){
        return dao.findById(id);
    }
}
