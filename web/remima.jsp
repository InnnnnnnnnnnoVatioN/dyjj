<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>


<html>


<head lang="en">


    <meta charset="utf-8"/>


    <title>个人信息</title>


    <link rel="stylesheet" type="text/css" href="css/public.css"/>


    <link rel="stylesheet" type="text/css" href="css/mygrxx.css"/>


</head>


<body><!------------------------------head------------------------------>
<%@include file="include_header.jsp"%>
<!------------------------------idea------------------------------>


<div class="address mt">


    <div class="wrapper clearfix">
        <a href="index.jsp" class="fl">首页</a><span>/</span>
        <a href="mygxin.jsp" class="on">个人信息</a>
    </div>


</div><!------------------------------Bott------------------------------>


<div class="Bott">
    <div class="wrapper clearfix">

        <%@include file="myLeft.jsp"%>
        <div class="you fl"><h2>修改密码</h2>
            <form action="#" method="get" class="remima"><p><span>选择验证身份方式：</span><input type="checkbox"/>密码验证 <input
                    type="checkbox"/>邮箱验证</p>
                <p><span>原密码：</span><input type="text"/></p>
                <p class="op">输入原密码</p>
                <p><span>新密码：</span><input type="text"/></p>
                <p class="op">6-16 个字符，需使用字母、数字或符号组合，不能使用纯数字、纯字母、纯符号</p>
                <p><span>重复新密码：</span><input type="text"/></p>


                <p class="op">请再次输入密码</p>


                <p><span>验证码：</span><input type="text"/><img src="img/temp/code.jpg" alt=""/></p>


                <p class="op">按右图输入验证码，不区分大小写</p><input type="submit" value="提交"/></form>


        </div>


    </div>


</div><!--返回顶部-->


<div class="gotop"><a href="cart.jsp">


    <dl>


        <dt><img src="img/gt1.png"/></dt>


        <dd>去购<br/>物车</dd>


    </dl>


</a><a href="#" class="dh">


    <dl>


        <dt><img src="img/gt2.png"/></dt>


        <dd>联系<br/>客服</dd>


    </dl>


</a><a href="mygxin.jsp">


    <dl>


        <dt><img src="img/gt3.png"/></dt>


        <dd>个人<br/>中心</dd>


    </dl>


</a><a href="#" class="toptop" style="display: none">


    <dl>


        <dt><img src="img/gt4.png"/></dt>


        <dd>返回<br/>顶部</dd>


    </dl>


</a>


    <p>18516955565</p></div><!--footer-->


<div class="footer">


    <div class="top">


        <div class="wrapper">


            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot1.png"/></a><span class="fl">7天无理由退货</span>


            </div>


            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot2.png"/></a><span class="fl">15天免费换货</span>


            </div>


            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot3.png"/></a><span class="fl">满599包邮</span>


            </div>


            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot4.png"/></a><span class="fl">手机特色服务</span>


            </div>


        </div>


    </div>


    <p class="dibu">渡一家居&copy;为您的家庭增添一抹温馨<br/>


        本网站数据用作学习交流使用， 如有侵权 ，请联系</p></div>


<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>


<script src="js/public.js" type="text/javascript" charset="utf-8"></script>


<script src="js/user.js" type="text/javascript" charset="utf-8"></script>


</body>


</html>




