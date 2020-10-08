package com.dy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 设置编码的过滤器
 * tomcat8.0版本之前 , GET请求的请求编码无法设置.  需单独调整.
 */
public class CharSetFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //设置请求体的编码 (POST请求有效果)
        req.setCharacterEncoding("UTF-8");
        //设置响应体的内容类型 以及 编码
        resp.setContentType("text/json;charset=utf-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
