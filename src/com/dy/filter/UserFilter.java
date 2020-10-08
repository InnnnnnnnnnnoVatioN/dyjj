package com.dy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/mygxin.jsp","/order.jsp","/myorderq.jsp","/myprod.jsp","/mygxin.jsp","/address.jsp","/mygrxx.jsp","/remima.jsp"})
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req , ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.    判断session中是否存在user数据
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if(user == null){
            String url = request.getRequestURL().toString();
            String para = request.getQueryString();
            if(para!=null){
                url += "?"+para;
            }
            session.setAttribute("loginUrl",url);
            response.sendRedirect("login.jsp");
        }else{
            //放行
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
