package com.dy.bean;

/**
 * 商品分类
 */
public class GoodsClass {

    //id
    private int id;
    //父分类id
    private int parentId;
    //分类名称
    private String className;

    public GoodsClass() {
    }

    public GoodsClass(int id, int parentId, String className) {
        this.id = id;
        this.parentId = parentId;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "编号:" + id +
                "\t父编号:" + parentId +
                "\t 分类名称:" + className+"\r\n";
    }
}
