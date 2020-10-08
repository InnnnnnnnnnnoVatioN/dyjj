package com.dy.servlet.address;

import com.dy.bean.Address;
import com.dy.bean.Message;
import com.dy.bean.User;
import com.dy.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addressAdd.do")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        int code1 = Integer.parseInt(request.getParameter("code1"));
        int code2 = Integer.parseInt(request.getParameter("code2"));
        int code3 = Integer.parseInt(request.getParameter("code3"));
        int code4 = Integer.parseInt(request.getParameter("code4"));
        String userAddress = request.getParameter("userAddress");
        String defaultFlag = request.getParameter("new");
        Address address = new Address(-1,userId,userName,userPhone,code1,code2,code3,code4,userAddress,0,null);
        Message msg = new Message();
        if("1".equals(defaultFlag)){
            //第一次新增地址，  新增默认地址
            AddressService.insertDefault(address,userId);
            msg.setStatus(1);
        }else{
            //新增普通地址
            AddressService.insert(address);
            msg.setStatus(0);
        }
        msg.setData(address);
        response.getWriter().append(msg.toJson());
    }
}
