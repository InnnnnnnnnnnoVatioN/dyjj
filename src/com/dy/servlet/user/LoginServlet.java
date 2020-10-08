package com.dy.servlet.user;

import com.dy.bean.Carts;
import com.dy.bean.Message;
import com.dy.bean.User;
import com.dy.service.SqlCartsService;
import com.dy.service.UserService;

import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.    接收客户端传递的帐号和密码
        String userPhone = request.getParameter("userPhone");
        String password = request.getParameter("password");
        //2.    调用service , 进行登录
        User u = new User(userPhone,password);
        boolean flag = UserService.login(u);
        //3.    根据登录的结果 进行 响应数据的准备Message
        Message msg = null;
        if(flag){
            String loginUrl = (String) request.getSession().getAttribute("loginUrl");
            //4.    在登录成功时,  将用户信息存储在session中. 便于后续使用.
            msg = new Message(200,"恭喜你, 登录成功了!");
            HttpSession session = request.getSession();
            session.setAttribute("user",u);

            if(loginUrl != null){
                //因为被拦截 所以登录. loginUrl拦截的地址
                msg.setStatus(201);
                msg.setData(loginUrl);
                request.getSession().removeAttribute("loginUrl");
            }


            //面向切面 , aop
            //合并购物车
            updateCartStatus(session);
        }else{
            msg = new Message(-1,"很遗憾 , 帐号或密码错误");
        }

        response.getWriter().append(msg.toJson());
        

    }

    private void updateCartStatus(HttpSession session) {

        ArrayList<Carts> data = (ArrayList<Carts>) session.getAttribute("carts");
        User user = (User) session.getAttribute("user");

        if(data!=null && data.size()>0){
            session.removeAttribute("carts");
            session.removeAttribute("cartsCount");
            //需要合并
            new Thread(){
                @Override
                public void run() {
                    SqlCartsService service = new SqlCartsService();
                    service.insertSessionCarts(user.getId(),data);
                }
            }.start();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
