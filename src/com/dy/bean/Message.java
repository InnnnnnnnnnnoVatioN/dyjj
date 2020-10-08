package com.dy.bean;

import com.google.gson.Gson;

//{"status":0,"msg":"消息内容",data:{}}
public class Message {
    private int status;
    private String msg;
    private Object data;

    public Message() {
    }

    public Message(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Message(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Message(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public String toJson(){
        return new Gson().toJson(this);
    }

}
