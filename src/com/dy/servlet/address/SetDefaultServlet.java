package com.dy.servlet.address;

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

@WebServlet("/addressSetDefault.do")
public class SetDefaultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean flag = AddressService.updateDefaultById(Integer.parseInt(id),user.getId());
        Message msg = null;
        if(flag){
            msg = new Message(200,"设置成功");
        }else{
            msg = new Message(-1,"设置失败");
        }
        response.getWriter().append(msg.toJson());
    }
}
