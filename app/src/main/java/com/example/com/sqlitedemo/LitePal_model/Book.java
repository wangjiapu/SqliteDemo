package com.example.com.sqlitedemo.LitePal_model;

import org.litepal.crud.DataSupport;

/**
 * Created by 蒲家旺 on 2017/7/22.
 * 配置流程:
 *
 * 1. compile 'org.litepal.android:core:1.3.2'
 * 2.app/src/main->new ->Directory->assets目录->litepal.xml
 * 3.manifest->application->android:name="org.litepal.LitePalApplication"
 *
 */

public class Book extends DataSupport{
    private int id;
    private String name;
    private  double price;
    private String auther;

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name+"--"+id+"--"+auther+"--"+price;
    }
}
