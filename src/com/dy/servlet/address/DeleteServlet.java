package com.dy.servlet.address;

import com.dy.bean.Message;
import com.dy.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addressDelete.do")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        boolean flag = AddressService.deleteById(Integer.parseInt(id));
        Message msg = null;
        if(flag){
            msg = new Message(200,"删除成功");
        }else{
            msg = new Message(-1,"删除失败");
        }
        response.getWriter().append(msg.toJson());
    }
}
