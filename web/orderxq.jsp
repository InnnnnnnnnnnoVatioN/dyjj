<%@ page import="com.dy.bean.*" %>
<%@ page import="com.dy.service.*" %><%--
  Created by IntelliJ IDEA.
  User: LWJ
  Date: 2020/2/21
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/myorder.css"/>
</head>
<body><!------------------------------head------------------------------>
<%@include file="include_header.jsp"%>
<%
    String orderId = request.getParameter("orderId");
    Order o = OrderService.findById(Integer.parseInt(orderId));
    List<OrderGoods> ogs = OrderGoodsService.findByOrderId(Integer.parseInt(orderId));
    Address address = AddressService.findById(o.getAddressId());
    Payments pay = PaymentsService.findById(o.getPaymentId());
    Transports tr = TransportsService.findById(o.getTransportId());
%>



<!------------------------------idea------------------------------>
<div class="address mt">
    <div class="wrapper clearfix"><a href="index.jsp" class="fl">首页</a><span>/</span><a href="myorderq.jsp"
                                                                                         class="on">我的订单</a><span>/</span><a
            href="#" class="on">订单详情</a></div>
</div><!------------------------------Bott------------------------------>
<div class="Bott">
    <div class="wrapper clearfix">
        <div class="zuo fl">
            <h3><a href="#"><img src="img/tx.png"/></a>
                <p class="clearfix"><span class="fl">[羊羊羊]</span><span class="fr">[退出登录]</span></p></h3>
            <div><h4>我的交易</h4>
                <ul>
                    <li><a href="cart.jsp">我的购物车</a></li>
                    <li class="on"><a href="myorderq.jsp">我的订单</a></li>
                    <li><a href="myprod.jsp">评价晒单</a></li>
                </ul>
                <h4>个人中心</h4>
                <ul>
                    <li><a href="mygxin.jsp">我的中心</a></li>
                    <li><a href="address.jsp">地址管理</a></li>
                </ul>
                <h4>账户管理</h4>
                <ul>
                    <li><a href="mygrxx.jsp">个人信息</a></li>
                    <li><a href="remima.jsp">修改密码</a></li>
                </ul>
            </div>
        </div>
        <div class="you fl">
            <div class="my clearfix"><h2>订单详情<a href="#">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a></h2>
                <h3>订单号：<span>000000<%=o.getId()%></span></h3></div>
            <div class="orderList">
                <div class="orderList1"><h3><%=o.getStatus()==0?"待收货":"已完成"%></h3>
                    <%
                        for(OrderGoods og:ogs){
                            %>
                            <div style="margin-top: 5px" class="clearfix">
                                <a href="findGoods.do?goodsId=<%=og.getGoodsId()%>" class="fl"><img style="width:87px;height:87px" src="img/small/<%=og.getGoodsImg()%>"/></a>

                                <p class="fl"><a href="findGoods.do?goodsId=<%=og.getGoodsId()%>"><%=og.getGoodsName()%></a><a href="javascript:void(0)">¥<%=og.getGoodsPrice()%>×<%=og.getGoodsNum()%></a></p>
                            </div>
                    <%
                        }
                    %>
                </div>

                <div class="orderList1"><h3>收货信息</h3>
                    <p>姓 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span><%=address.getUserName()%></span></p>
                    <p>联系电话：<span><%=address.getUserPhone()%></span></p>
                    <p>收货地址：<span>
                        <%=RegionService.findByCode(address.getProvinceId()+"").getName()%>
                        <%=RegionService.findByCode(address.getCityId()+"").getName()%>
                        <%=RegionService.findByCode(address.getAreaId()+"").getName()%>
                        <%=RegionService.findByCode(address.getStreetId()+"").getName()%>
                    </span></p></div>
                <div class="orderList1"><h3>支付方式及送货时间</h3>
                    <p>支付方式：<span><%=pay.getName()%></span></p>
                    <p>送货时间：<span>不限送货时间</span></p></div>
                <div class="orderList1 hei"><h3><strong>商品总价：</strong><span>¥<%=o.getMoney()%></span></h3>
                    <p><strong>运费：</strong><span>¥0</span></p>
                    <p><strong>订单金额：</strong><span>¥<%=o.getMoney()%></span></p>
                    <p><strong>实付金额：</strong><span>¥<%=o.getMoney()%></span></p></div>
            </div>
        </div>
    </div>
</div>
<<!--返回顶部-->
<%@include file="footer.jsp"%>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>

