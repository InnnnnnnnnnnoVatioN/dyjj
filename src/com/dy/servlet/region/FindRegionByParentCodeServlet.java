package com.dy.servlet.region;

import com.dy.bean.CnRegion;
import com.dy.bean.Message;
import com.dy.service.RegionService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getRegionByParentCode.do")
public class FindRegionByParentCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        List<CnRegion> citys = RegionService.findByParentCode(code);
        Message msg = new Message(200,"",citys);
        //{status:200,msg:"",citys:[{id:xx,code:xx,name:xx},...]}
        response.getWriter().append(msg.toJson());
    }
}
