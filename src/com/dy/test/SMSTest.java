package com.dy.test;

import com.dy.util.SendSms;

public class SMSTest {
    public static void main(String[] args) {
        int num = SendSms.random();
        System.out.println("发送的验证码内容是:"+num);
        boolean flag = SendSms.send("15668415101", num);
        System.out.println(flag?"发送成功":"发送失败");
    }
}
