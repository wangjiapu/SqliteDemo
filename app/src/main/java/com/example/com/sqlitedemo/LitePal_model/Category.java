package com.example.com.sqlitedemo.LitePal_model;


import org.litepal.crud.DataSupport;

public class Category extends DataSupport {
    private int id;
    private String num;

    public int getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
