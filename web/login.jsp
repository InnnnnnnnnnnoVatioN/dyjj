<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>登录</title>

    <link rel="stylesheet" type="text/css" href="css/public.css"/>

    <link rel="stylesheet" type="text/css" href="css/login.css"/>

</head>

<body><!-------------------login-------------------------->

<div class="login">

    <form action="#" method="post" onsubmit="login();return false;"><h1><a href="index.jsp"><img src="img/temp/logo.png"></a></h1>

        <p></p>

        <div class="msg-warn hide"><b></b>公共场所不建议自动登录，以防账号丢失</div>

        <p><input type="text" id="userphone" name="" value="" placeholder="手机号"></p>

        <p><input id="password" type="password" name="" value="" placeholder="密码"></p>

        <p><input type="submit" name="" value="登  录"></p>

        <p class="txt"><a class="" href="reg.jsp">免费注册</a><a href="forget.jsp">忘记密码？</a></p></form>

</div>

<script src="js/jquery.min.js"></script>

<script src="js/layer/layer.js"></script>

<script>

    function login(){

        //1.   获取用户输入的手机号和密码

        //userphone.value;

        //password.value;

        //2.    转圈

        layer.load();

        //3.    向服务器发送ajax请求

        $.post("login.do","userPhone="+userphone.value+"&password="+password.value,function(data){

            //4.    接收服务器响应的内容. 并处理

            layer.closeAll();

            layer.msg(data.msg);

            if(data.status == 200){

                window.location.href="index.jsp";

            }else if(data.status == 201){

                window.location.href=data.data;

            }

        });


    }

</script>

</body>

</html>



