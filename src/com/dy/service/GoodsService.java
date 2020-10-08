package com.dy.service;

import com.dy.bean.Carts;
import com.dy.bean.Goods;
import com.dy.dao.BaseGoodsDao;
import com.dy.dao.GoodsDaoImp;

import java.util.List;

public class GoodsService {

    private static BaseGoodsDao dao = new GoodsDaoImp();

    //查询所有商品
    public static List<Goods> findAll(int orderBy){
        return dao.findAll(orderBy);
    }
    /**
     * 根据一级分类查询商品
     * @param class1Id  一级分类编号
     * @param page  分页查询的页数
     * @param size  每页显示的数据数量
     * @param orderBy 0正序 1倒序
     * @return 查询结果
     */
    public static List<Goods> findGoodsByClass1(int class1Id,int page,int size,int orderBy){
        return dao.findGoodsByClass1(class1Id,page,size,orderBy);
    }
    /**
     * 根据二级分类查询商品
     * @param class2Id  二级分类编号
     * @param page  分页查询的页数 页数从1开始
     * @param size  每页显示的数据数量
     * @param orderBy 0正序 1倒序
     * @return 查询结果
     */
    public static List<Goods> findGoodsByClass2(int class2Id,int page,int size,int orderBy){
        return dao.findGoodsByClass2(class2Id,(page-1)*size,size,orderBy);
    }
    /**
     * 根据用户输入的商品名称, 模糊查询商品
     * @param name  二级分类编号
     * @param page  分页查询的页数
     * @param size  每页显示的数据数量
     * @param orderBy 0正序 1倒序
     * @return 查询结果
     */
    public static List<Goods> findGoodsLikeName(String name,int page,int size,int orderBy){
        return dao.findGoodsLikeName(name,page,size,orderBy);
    }

    /**
     * 根据商品编号查询单个商品详情
     * @param id 要查询的商品编号
     * @return 查询的结果
     */
    public static Goods findGoodsById(int id){
        return dao.findGoodsById(id);
    }

    /**
     * 根据购物车查询商品信息
     * @param cs
     * @return
     */
    public static List<Goods> findGoodsByCarts(List<Carts> cs){
        return dao.findGoodsByCarts(cs);
    }
}
