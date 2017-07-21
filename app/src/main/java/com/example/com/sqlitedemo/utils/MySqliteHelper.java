package com.example.com.sqlitedemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySqliteHelper extends SQLiteOpenHelper {

    /**
     * 构造函数
     * @param context  上下文
     * @param name    数据库名称
     * @param factory  游标工厂
     * @param version   版本号
     */
    public MySqliteHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context){
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }
    /**
     * 创建时回掉函数
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("tag","---------create----------");
        //创建表
        String sql="create table "+Constant.TABLE_NAME+"("+
                Constant._ID+" Integer primary key," +
                Constant.AGE+" Integer," +
                Constant.NAME+" varchar(10)"+
                ")";
        db.execSQL(sql);//执行
        Log.e("建立表","成功");
    }

    /**
     * 更新函数时调用
     * @param db  数据库对象
     * @param oldVersion  旧的版本号
     * @param newVersion   新的版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.e("tag","---------onOpen----------");

    }
}
