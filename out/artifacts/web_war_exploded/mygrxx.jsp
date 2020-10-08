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


    <div class="wrapper clearfix"><a href="index.jsp" class="fl">首页</a><span>/</span><a href="mygxin.jsp" class="on">个人信息</a>


    </div>


</div><!------------------------------Bott------------------------------>


<div class="Bott">


    <div class="wrapper clearfix">
        <%@include file="myLeft.jsp"%>
        <div class="you fl"><h2>个人信息</h2>


            <div class="gxin">


                <div class="tx"><a href="#"><img src="img/tx.png"/>


                    <p id="avatar">修改头像</p></a></div>


                <div class="xx"><h3 class="clearfix"><strong class="fl">基础资料</strong><a href="#" class="fr" id="edit1">编辑</a>


                </h3>


                    <div>姓名：六六六</div>


                    <div>生日：1995-06-06</div>


                    <div>性别：女</div>


                    <h3>高级设置</h3><!--<div><span class="fl">银行卡</span><a href="#" class="fr">管理</a></div>-->


                    <div><span class="fl">账号地区：中国</span><a href="#" class="fr" id="edit2">修改</a></div>


                </div>


            </div>


        </div>


    </div>


</div><!--遮罩-->


<div class="mask"></div><!--编辑弹框-->


<div class="bj">


    <div class="clearfix"><a href="#" class="fr gb"><img src="img/icon4.png"/></a></div>


    <h3>编辑基础资料</h3>


    <form action="#" method="get"><p><label>姓名：</label><input type="text" value="六六六"/></p>


        <p><label>生日：</label><input type="text"/></p>


        <p><label>性别：</label><span><input type="radio"/>男</span><span><input type="radio"/>女</span></p>


        <div class="bc"><input type="button" value="保存"/><input type="button" value="取消"/></div>


    </form>


</div><!--高级设置修改-->


<div class="xg">


    <div class="clearfix"><a href="#" class="fr gb"><img src="img/icon4.png"/></a></div>


    <h3>切换账号地区</h3>


    <form action="#" method="get"><p><label>姓名：</label><input type="text" value="六六六"/></p>


        <div class="bc"><input type="button" value="保存"/><input type="button" value="取消"/></div>


    </form>


</div><!--修改头像-->


<div class="avatar">


    <div class="clearfix"><a href="#" class="fr gb"><img src="img/icon4.png"/></a></div>


    <h3>修改头像</h3>


    <form action="#" method="get"><h4>请上传图片</h4><input type="button" value="上传头像"/>


        <p>jpg或png，大小不超过2M</p><input type="submit" value="提交"/></form>


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




