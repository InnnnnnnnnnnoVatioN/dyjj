package com.dy.servlet.cart;

import com.dy.bean.Carts;
import com.dy.bean.Message;
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

@WebServlet("/cartNumUpdate.do")
public class CartNumUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsId = request.getParameter("goodsId");
        String num = request.getParameter("num");
        HttpSession session = request.getSession();
        BaseCartsService service = null;
        if(session.getAttribute("user") == null){
            //用户未登录
            service = new SessionCartsService();
        }else{
            //用户已登录
            service = new SqlCartsService();
        }
        //200 / -1
        int flag = service.updateCartNum(session, Integer.parseInt(goodsId), Integer.parseInt(num));
        Message msg = new Message(flag,"");
        response.getWriter().append(msg.toJson());
    }
}
