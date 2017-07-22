package com.example.com.sqlitedemo.LitePal_model;


import android.database.sqlite.SQLiteDatabase;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * Created by 蒲家旺 on 2017/7/22.
 *
 */

public class LpUtils {

    public static SQLiteDatabase lpCreate_db() {
        return Connector.getDatabase();
    }
    public static void lpInsert_db() {
        for(int i=0;i<10;i++){
            Book book=new Book();
            book.setId(i);
            book.setName("c语言程序设计"+i);
            book.setAuther("张三"+i);
            book.setPrice(20.3);
            book.save();
        }
    }
    public static void lpDel_db() {
        DataSupport.delete(Book.class,6);
       // DataSupport.deleteAll(Book.class,"price<?","15");
    }

    public static String lpShow_db() {
        List<Book> books= DataSupport.findAll(Book.class);
        StringBuffer sb=new StringBuffer();
        for (Book b:books){
            sb.append(b.toString());
        }
        return sb.toString();
    }
    public static void lpUpdate_db() {
        //1。跟使用插入方法一样
        //2
        Book book=new Book();
        book.setPrice(100);
        book.updateAll("name=?","c语言程序设计1");
    }
}
