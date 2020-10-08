package com.dy.service;

import com.dy.bean.GoodsClass;
import com.dy.dao.BaseGoodsClassDao;
import com.dy.dao.GoodsClassDaoImp;

import java.util.List;

/**
 * 服务层
 */
public class GoodsClassService {

    private static BaseGoodsClassDao dao = new GoodsClassDaoImp();
    //查询所有分类
    public static List<GoodsClass> findAll(){
        return dao.findAll();
    }
    //根据id查询分类
    public static GoodsClass findClassById(int id){
        return dao.findClassById(id);
    }


}
