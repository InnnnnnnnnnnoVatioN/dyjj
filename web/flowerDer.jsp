<%@ page import="com.dy.util.DataUtil" %>
<%@ page import="com.dy.bean.GoodsClass" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dy.service.GoodsClassService" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dy.bean.Goods" %>
<%@ page import="com.dy.bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dy.service.GoodsService" %>
<%@ page import="com.google.gson.Gson" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>布艺软饰</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/proList.css"/>
</head>
<%--
    页面在访问时, 会传递不同的参数.
    1.  type
            0   :   查询所有商品
            1   :   一级分类商品
            2   :   二级分类商品
            3   :   模糊查询商品

    2.  classId1    :   一级分类商品查询时,. 传递的一级分类id
    3.  classId2    :   二级分类商品查询时,. 传递的二级分类id
    4.  goodsName   :   模糊查询商品时, 用户输入的内容
    5.  orderBy
    6.  class1Name  :   一级分类名称
    7.  class2Name  :   二级分类名称
--%>
    <%
        List<Goods> goodsList = null;

        String type = request.getParameter("type");
        String classId1 = request.getParameter("classId1");
        String classId2 = request.getParameter("classId2");
        String goodsName = request.getParameter("goodsName");
        String orderBy = request.getParameter("orderBy");
        String class1Name = request.getParameter("class1Name");
        String class2Name = request.getParameter("class2Name");


        int orderByInt = orderBy==null?0:Integer.parseInt(orderBy);
        if(type == null){
            type = "";
        }
        switch (type){
            case "0":
                //查询所有商品
                goodsList = GoodsService.findAll(orderByInt);
                break;
            case "1":
                //查询一级分类商品
                goodsList = GoodsService.findGoodsByClass1(Integer.parseInt(classId1),1,50,orderByInt);
                break;
            case "2":
                //查询二级分类商品
                goodsList = GoodsService.findGoodsByClass2(Integer.parseInt(classId2),1,50,orderByInt);
                break;
            case "3":
                //模糊查询商品
                goodsList = GoodsService.findGoodsLikeName(goodsName,1,50,orderByInt);
                class1Name = goodsName;
                break;
        }
    %>



<body><!------------------------------head------------------------------>
<jsp:include page="include_header.jsp"></jsp:include>
<!------------------------------banner------------------------------>
<div class="banner"><a href="#"><img src="img/temp/banner1.jpg"/></a></div>
<!-----------------address------------------------------->
<div class="address">
    <div class="wrapper clearfix">
        <a href="index.jsp">首页</a>
        <span>/</span>
        <%
            if(class1Name.equals("所有商品") || type.equals("3")){
                %>
        <a href="" class="on"><%=class1Name%></a>
                <%
            }else{
                %>
        <a href="flowerDer.jsp?type=1&classId1=<%=classId1%>&class1Name=<%=class1Name%>" class="on"><%=class1Name%></a>

        <%
            }
        %>
        <%
            if(class2Name != null){
                %>
                    <span>/</span>
                    <a href="flowerDer.jsp?type=2&classId1=<%=classId1%>&classId2=<%=classId2%>&class1Name=<%=class1Name%>&class2Name=<%=class2Name%>" class="on"><%=class2Name%></a>
                <%
            }
        %>
    </div>
</div><!-------------------current---------------------->
<div class="current">
    <div class="wrapper clearfix"><h3 class="fl"><%=class2Name == null?class1Name:class2Name%></h3>
        <div class="fr choice"><p class="default"><%=orderByInt==0?"价格从低到高":"价格从高到低"%></p>
            <ul class="select">
                <%--
                    js中的window.location.href 进行页面的跳转
                    js中的window.location.replace 进行页面替换

                --%>
                <li onclick="orderBy(0)">价格从高到低</li>
                <li onclick="orderBy(1)">价格从低到高</li>
                <script>
                    function orderBy(type){
                        //JS: 获取当前浏览器地址栏中的内容
                        var url = window.location.href;
                        url = url.replace("&orderBy=1","");
                        if(type == 0){
                            // flowerDer.jsp?type=1&classId1=4&orderBy=1
                            url = url+"&orderBy=1";
                        }
                        if(url != window.location.href){
                            //经过计算的网址, 跟原网址一致, 无序跳转
                            window.location.replace(url);
                        }

                    }
                </script>
            </ul>
        </div>
    </div>
</div><!----------------proList------------------------->
<ul class="proList wrapper clearfix">

    <%
        if(goodsList == null || goodsList.size() == 0){
            //没有商品
            %>
                <h1 align="center">很遗憾, 商品不存在</h1>
            <%
        }else{
        for(Goods g:goodsList){

            //是一个JSON格式的数组
            String imgs = g.getImgs();//['xxx.jpg','xxxx.jpg']
            String imgPath = null;
            if("[".equals(imgs)){
                imgPath = "errorGoodsImg.jpg";
            }else{
                Gson gson = new Gson();
                List<String> imgsList = gson.fromJson(imgs, List.class);
                imgPath = imgsList.get(0);
            }



    %>
                <%--
                    点击商品, 跳转到商品页面

                --%>
                <li><a href="findGoods.do?goodsId=<%=g.getId()%>">
                    <dl>
                        <dt><img src="img/imgs/<%=imgPath%>"></dt>
                        <dd>【渡一】<%=g.getName()%></dd>
                        <dd>￥<%=g.getPrice()%></dd>
                    </dl>
                </a></li>
            <%

        }

                }
    %>




</ul><!----------------mask------------------->
<div class="mask"></div><!-------------------mask内容------------------->
<div class="proDets"><img class="off" src="img/temp/off.jpg"/>
    <div class="tit clearfix"><h4 class="fl">【渡一】非洲菊仿真花干花</h4><span class="fr">￥17.90</span></div>
    <div class="proCon clearfix">
        <div class="proImg fl"><img class="list" src="img/temp/proDet.jpg"/>
            <div class="smallImg clearfix"><img src="img/temp/proDet01.jpg" data-src="img/temp/proDet01_big.jpg"><img
                    src="img/temp/proDet02.jpg" data-src="img/temp/proDet02_big.jpg"><img src="img/temp/proDet03.jpg"
                                                                                          data-src="img/temp/proDet03_big.jpg"><img
                    src="img/temp/proDet04.jpg" data-src="img/temp/proDet04_big.jpg"></div>
        </div>
        <div class="fr">
            <div class="proIntro"><p>颜色分类</p>
                <div class="smallImg clearfix categ"><p class="fl"><img src="img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花"
                                                                        data-src="img/temp/proBig01.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草"
                                       data-src="img/temp/proBig02.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall03.jpg" alt="20支快乐花" data-src="img/temp/proBig03.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="img/temp/proBig04.jpg"></p>
                </div>
                <p>数量</p>
                <div class="num clearfix"><img class="fl sub" src="img/temp/sub.jpg"><span class="fl"
                                                                                           contentEditable="true">1</span><img
                        class="fl add" src="img/temp/add.jpg">
                    <p class="please fl">请选择商品属性!</p></div>
            </div>
            <div class="btns clearfix"><a href="#2"><p class="buy fl">立即购买</p></a><a href="#2"><p class="cart fr">
                加入购物车</p></a></div>
        </div>
    </div>
    <a class="more" href="proDetail.jsp">查看更多细节</a></div><!--返回顶部-->
<%@include file="footer.jsp"%>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>

