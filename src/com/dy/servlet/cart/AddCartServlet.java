package com.dy.servlet.cart;

import com.dy.bean.Carts;
import com.dy.bean.Message;
import com.dy.bean.User;
import com.dy.service.BaseCartsService;
import com.dy.service.SessionCartsService;
import com.dy.service.SqlCartsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addCart.do")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //2.	在addCart.do这个servlet中, 接收商品id
        String id = request.getParameter("goodsId");
        HttpSession session  = request.getSession();
        BaseCartsService service = null;

        if(session.getAttribute("user") == null){
            //未登录
            service = new SessionCartsService();
        }else{
            //已登录
            service = new SqlCartsService();
        }

        service.addCart(session,Integer.parseInt(id));
        //4.	返回数据 {status:200,msg:"购物车加入成功"}
        Message msg = new Message(200,"购物车加入成功");
        response.getWriter().append(msg.toJson());
    }


}
