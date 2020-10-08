package com.dy.service;

import com.dy.bean.Carts;
import com.dy.bean.Message;
import com.dy.bean.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class SessionCartsService implements BaseCartsService {
    /**
     * 对于商品的增加
     *
     * @param session
     * @param goodsId
     */
    @Override
    public void addCart(HttpSession session, int goodsId) {

            //3.	进行session中商品数据的购物车存储.
            //  1.	取出session中的一个arrayList<Carts>
            ArrayList<Carts> data = (ArrayList<Carts>) session.getAttribute("carts");
            if(data == null){
                //购物车从未存储过商品
                data = new ArrayList<>();
                session.setAttribute("carts",data);
            }
            //  2.	使用arrayList的indexOf 判断购物车是否在集合中存在
            Carts c = new Carts(goodsId);
            int index = data.indexOf(c);
            if(index != -1){
                //查找到, 集合中存在此购物车
                //  3.	如果存在 ,则根据indexOf返回的下标, 找到此购物车 进行数量的加一
                Carts oldCarts = data.get(index);
                oldCarts.setCartNum(oldCarts.getCartNum()+1);
            }else{
                //  4.	如果不存在 , 将其存储在集合中
                c.setCartNum(1);
                //  此时存储在集合中的购物车, 拥有如下信息:1 商品ID,2 商品数量1 , 3.默认选中
                data.add(c);
            }
        /*}
        else{
            //3.	根据商品id+用户id从数据库中 查询购物车信息 (判断此商品在购物车表中是否存在)
            //4.	如果存在 , 则修改数据为 数量加一
            //5.	如果不存在 , 则将数据插入到表格中.
            //6.	返回数据 {status:200,msg:"购物车加入成功"}
        }*/

        Integer count = (Integer) session.getAttribute("cartsCount");
        if(count == null){
            count = 0;
        }
        count++;
        session.setAttribute("cartsCount",count);
    }

    /**
     * 对于商品数量的修改
     *
     * @param session
     * @param goodsId 要修改的商品id
     * @param num     -1 或 1
     */
    @Override
    public int updateCartNum(HttpSession session, int goodsId, int num) {
        ArrayList<Carts> cs = (ArrayList<Carts>) session.getAttribute("carts");
        if(cs == null){
            cs = new ArrayList<>();
        }
        Carts c = new Carts(goodsId);
        int i = cs.indexOf(c);
        if(i!=-1){
            cs.get(i).setCartNum(cs.get(i).getCartNum()+num);
            Integer count = (Integer) session.getAttribute("cartsCount");
            session.setAttribute("cartsCount",count+num);
            return 200;
        }else{
            return -1;
        }
    }

    /**
     * 用于选中 或 取消选中购物车
     *
     * @param session
     * @param goodsId 选中或取消选中的商品id
     * @param isCheck 选中0  或 取消选中1
     * @return 处理的结果 ,200成功  -1表示失败
     */
    @Override
    public int cartCheck(HttpSession session, int goodsId, int isCheck) {
        ArrayList<Carts> cs = (ArrayList<Carts>) session.getAttribute("carts");
        int index = indexOf(cs, goodsId);
        if(index == -1){
            return -1;
        }else{
            cs.get(index).setIsCheck(isCheck);
            return 200;
        }
    }

    /**
     * 用于从购物车列表中 移除一个商品的购物车
     *
     * @param session
     * @param goodsId 要移除的商品id
     * @return 200 成功 -1 表示失败
     */
    @Override
    public int cartDel(HttpSession session, int goodsId) {
        ArrayList<Carts> cs = (ArrayList<Carts>) session.getAttribute("carts");
        int index = indexOf(cs, goodsId);
        if(index == -1){
            return -1;
        }else{
            Carts c = cs.remove(index);
            Integer count = (Integer) session.getAttribute("cartsCount");
            session.setAttribute("cartsCount",count-c.getCartNum());
            return 200;
        }
    }


    private int indexOf(ArrayList<Carts> cs,int goodsId){
        if(cs == null){
            return -1;
        }
        return cs.indexOf(new Carts(goodsId));
    }
}
