<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>注册</title>

    <link rel="stylesheet" type="text/css" href="css/public.css"/>

    <link rel="stylesheet" type="text/css" href="css/login.css"/>

</head>

<body><!-------------------reg-------------------------->

<div class="reg">

    <form action="" onsubmit="reg();return false;" method="post"><h1><a href="index.jsp"><img src="img/temp/logo.png"></a></h1>

        <p>用户注册</p>

        <p><input id="userPhone" type="text" name="" value="" placeholder="请输入手机号码"></p>

        <p><input id="password1" type="password" name="" value="" placeholder="请输入密码"></p>

        <p><input id="password2" type="password" name="" value="" placeholder="请确认密码"></p>

        <p class="txtL txt"><input id="smsCode" class="code" type="text" name="" value="" placeholder="验证码" class="smsinput">

            <span class="sendsms" onclick="sendSms(this)" title="点击获取验证码">获取验证码</span>

        </p>

        <p><input type="submit" name="" value="注册"></p>

        <p class="txtL txt">完成此注册，即表明您同意了我们的<a href="#">

            <使用条款和隐私策略>

        </a></p>

        <p class="txt"><a href="login.jsp"><span></span>已有账号登录</a></p>

        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>

    <script>

        var smsButtonflag = true;

        function sendSms(span){

            if(smsButtonflag){

                var phoneRe = /^1(3[0-9]|47|5[012356789]|66|7[0-9]|8[0-9]|9[89])[0-9]{8}$/g;

                if(!phoneRe.test(userPhone.value)){

                    layer.msg("手机号码有误, 请检查");

                    return;

                }


                //1.    更改按钮标记， 不允许再次点击

                smsButtonflag = false;

                //2.    改变样式

                span.style.color = "#999";

                span.title = "请等待倒计时结束 ，再次获取";

                var s = 10;

                var iv = setInterval(function(){

                    s--;

                    span.innerHTML = s+"s";

                    if(s == 0){

                        smsButtonflag = true;

                        clearInterval(iv);

                        span.style.color = "";

                        span.title = "点击获取验证码";

                        span.innerHTML = "获取验证码";

                    }

                },1000)

                //3.    发送短信

                //3.1   先转圈

                layer.load();

                $.post("sms.do","status=1&userPhone="+userPhone.value,function(data){

                    layer.closeAll();

                    //data: {status:200|-1,msg:"发送成功|发送失败"}

                   layer.msg(data.msg);


                });

            }

        }


        function reg(){

            var userphone = userPhone.value;

            var pass1 = password1.value;

            var pass2 = password2.value;

            var code = smsCode.value;

            if(pass1 != pass2){

                layer.msg("两次密码输入不一致");

                return;

            }



            layer.load();

            $.post("reg.do","userPhone="+userphone+"&password="+pass1+"&smsCode="+code,function(data){

                layer.closeAll();

                layer.msg(data.msg)

                if(data.status == 200){

                    layer.msg("页面即将跳转到登录");

                    setTimeout(function(){

                        window.location.href="login.jsp";

                    },1000);

                }


            });

        }

    </script>

    <script src="js/jquery.min.js"></script>

    <script src="js/layer/layer.js"></script>

</div>

</body>

</html>



