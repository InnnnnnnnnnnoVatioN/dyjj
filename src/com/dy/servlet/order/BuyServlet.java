package com.dy.servlet.order;

import com.dy.bean.*;
import com.dy.service.GoodsService;
import com.dy.service.OrderGoodsService;
import com.dy.service.OrderService;
import com.dy.service.SqlCartsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buy.do")
public class BuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数:addressId=6&payId=1&trId=1&goodsId=217&goodsNum=5
        String addressId = request.getParameter("addressId");
        String payId = request.getParameter("payId");
        String trId = request.getParameter("trId");
        String goodsId = request.getParameter("goodsId");
        String goodsNum = request.getParameter("goodsNum");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Order order = new Order();
        order.setAddressId(Integer.parseInt(addressId));
        order.setPaymentId(Integer.parseInt(payId));
        order.setTransportId(Integer.parseInt(trId));
        order.setUserId(user.getId());
        List<OrderGoods> ogs = new ArrayList<>();
        if(goodsId == null){
            //从购物车进行的结算 , 需要从mysql中的购物车中查询商品信息.
            //1.    查询出所有选中的购物车
            SqlCartsService service = new SqlCartsService();
            List<Carts> cs = service.findByUserId(session, 0);
            //2.    删除所有选中的购物车
            service.cartDelByCheck(session,0);
            //3.    根据购物车 , 查询商品信息
            List<Goods> goods = GoodsService.findGoodsByCarts(cs);
            ArrayList<Goods> gs2 = new ArrayList<>();
            for(int i=0;i<cs.size();i++){
                Carts c = cs.get(i);
                for(int j=0;j<goods.size();j++){
                    if(c.getGoodsId() == goods.get(j).getId()){
                        gs2.add(goods.get(j));
                        break;
                    }
                }
            }
            goods = gs2;
            double money = 0;
            for(int i=0;i<cs.size();i++){
                money += cs.get(i).getCartNum() * goods.get(i).getPrice();
            }
            order.setMoney(money);
            int orderId = OrderService.insert(order);

            for(int i=0;i<cs.size();i++){
                OrderGoods og = new OrderGoods(-1,orderId,goods.get(i).getId(),cs.get(i).getCartNum(),goods.get(i).getPrice(),goods.get(i).getName(),goods.get(i).getImg().get(0));
                ogs.add(og);
            }
        }else{
            //立即购买
            List<Goods> goods = new ArrayList<>();
            Goods g = GoodsService.findGoodsById(Integer.parseInt(goodsId));
            goods.add(g);
            order.setMoney(g.getPrice()*Integer.parseInt(goodsNum));
            int orderId = OrderService.insert(order);
            //组装订单商品对象
            OrderGoods og = new OrderGoods(-1,orderId,Integer.parseInt(goodsId),Integer.parseInt(goodsNum),g.getPrice(),g.getName(),g.getImg().get(0));
            ogs.add(og);
        }
        OrderGoodsService.insert(ogs);
        response.sendRedirect("ok.jsp");
    }
}
