<%--
  Created by IntelliJ IDEA.
  User: LWJ
  Date: 2020/2/6
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.dy.util.DataUtil" %>
<%@ page import="com.dy.bean.GoodsClass" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dy.service.GoodsClassService" %>
<%@ page import="com.dy.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top"><h1 class="fl"><a href="index.jsp"><img src="img/logo.png"/></a></h1>
            <div class="fr clearfix" id="top1">
                <p class="fl">
                    <%
                        User user = (User) session.getAttribute("user");
                        if(user == null){
                         %>
                    <a href="login.jsp" id="login">登录</a>
                    <a href="reg.jsp" id="reg">注册</a>
                        <%
                        }else{%>
                    <a href="mygxin.jsp" id="login"><%=user.getNickName()==null?user.getUserPhone():user.getNickName()%></a>
                        <%
                        }
                    %>

                </p>
                <form action="flowerDer.jsp" method="get" class="fl">

                    <input type="text" name="goodsName" placeholder="热门搜索：干花花瓶"/><input
                        type="submit" value=""/><input type="hidden" name="type" value="3"></form>
                <div class="btn fl clearfix"><a href="mygxin.jsp"><img src="img/grzx.png"/></a><a href="#" class="er1"><img
                        src="img/ewm.png"/></a><a href="cart.jsp"><img src="img/gwc.png"/></a>
                    <p><a href="#"><img src="img/smewm.png"/></a></p></div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="index.jsp">首页</a></li>
            <li><a href="flowerDer.jsp?type=0&class1Name=所有商品">所有商品</a>
            </li>

            <%

                //1.    从缓存中取出所有的分类数据
                List<HashMap<String,Object>> class1 = (List<HashMap<String,Object>>)DataUtil.data.get("class");
                if(class1 == null){
                    /*new Thread(){
                        @Override
                        public void run() {
                            while(true){
                                try {
                                    Thread.sleep(100000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();*/


                    //2.    从未存储过 , 需要从数据库查询出所有分类
                    List<GoodsClass> data = GoodsClassService.findAll();
                    //3.    因为数据库查询的数据 是不区分层级的.  所以对其层级进行划分
                    //3.1   创建一个新的集合 , 这个集合中存储是所有的一级分类. 以及 一级分类所属的二级分类数据
                    class1 = new ArrayList<>();
                    //3.2   遍历所有分类
                    for(int i=0;i<data.size();i++){
                        GoodsClass goodsClass = data.get(i);
                        //3.3   取出所有分类的父id , 如果是0 ,则表示是一级分类
                        if(goodsClass.getParentId() == 0){
                            //3.4   创建一个map集合, 用于存储这个一级分类的分类对象, 和 它所属的所有二级分类的分类对象
                            HashMap<String,Object> data2 = new HashMap<>();
                            //3.5   存储这个一级分类的分类对象
                            data2.put("class1",goodsClass);
                            //3.6   创建一个List集合, 用于存储所有的二级分类
                            ArrayList<GoodsClass> class2 = new ArrayList<>();
                            //3.7   遍历所有的分类.
                            for(int j=0;j<data.size();j++){
                                //3.8   并判断每一个分类的父id 是否是当前一级分类的id
                                if(data.get(j).getParentId() == goodsClass.getId()){
                                    //3.9   找到了一个属于这个一级分类的 二级分类对象 , 将其存储到list集合中
                                    class2.add(data.get(j));
                                }
                            }
                            data2.put("class2",class2);
                            //Java中的引用传递
                            class1.add(data2);
                        }
                    }
                    DataUtil.data.put("class",class1);
                }
            %>
            <%
                //循环遍历分类集合 class1
                for(int i=0;i<class1.size();i++){
                    //获取每一个一级分类集合
                    HashMap<String, Object> data = class1.get(i);
                    //一级分类对象
                    GoodsClass cla1 = (GoodsClass)data.get("class1");
                    //二级 分类对象
                    List<GoodsClass> cla2 = (List<GoodsClass>)data.get("class2");
            %>
            <li><a href="flowerDer.jsp?type=1&classId1=<%=cla1.getId()%>&class1Name=<%=cla1.getClassName()%>"><%=cla1.getClassName()%></a>
                <div class="sList2">
                    <div class="clearfix">
                        <%
                            for(GoodsClass goodsClass:cla2){
                        %>
                        <a href="flowerDer.jsp?type=2&classId1=<%=cla1.getId()%>&classId2=<%=goodsClass.getId()%>&class1Name=<%=cla1.getClassName()%>&class2Name=<%=goodsClass.getClassName()%>"><%=goodsClass.getClassName()%></a>
                        <%
                            }
                        %>


                    </div>
                </div>
            </li>
            <%
                }
            %>


        </ul>
    </div>
</div>

