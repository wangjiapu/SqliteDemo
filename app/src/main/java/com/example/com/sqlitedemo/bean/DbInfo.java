package com.example.com.sqlitedemo.bean;

/**
 * Created by 蒲家旺 on 2017/7/21.
 */

public class DbInfo {
    private String name;
    private int id;
    private int age;

    @Override
    public String toString() {
        return name+","+age+","+id;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
