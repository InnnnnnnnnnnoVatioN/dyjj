package com.dy.servlet.cart;

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

//goodsId=258&isCheck=1
@WebServlet("/cartCheck.do")
public class CartCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsId = request.getParameter("goodsId");
        String isCheck = request.getParameter("isCheck");
        HttpSession session = request.getSession();
        BaseCartsService service = null;
        if(session.getAttribute("user") == null){
            //未登录
            service = new SessionCartsService();
        }else{
            //已登录
            service = new SqlCartsService();
        }
        int flag = service.cartCheck(session, Integer.parseInt(goodsId), Integer.parseInt(isCheck));
        Message msg = new Message(flag,"");
        response.getWriter().append(msg.toJson());
    }

}
