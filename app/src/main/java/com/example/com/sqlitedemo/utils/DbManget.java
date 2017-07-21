package com.example.com.sqlitedemo.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.com.sqlitedemo.bean.DbInfo;

import java.util.ArrayList;
import java.util.List;


public class DbManget {
    private static MySqliteHelper helper;
    public static MySqliteHelper getIntance(Context context){
        if (helper==null){
            helper=new MySqliteHelper(context);
        }
        return helper;
    }

    public static void execSQL(SQLiteDatabase db,String sql){
        if (db!=null){
            if (!sql.isEmpty()){
                db.execSQL(sql);
            }
        }
    }

    public static List<DbInfo> cursor2List(Cursor cursor){
        List<DbInfo> list=new ArrayList<>();
        while (cursor.moveToNext()){
            DbInfo person=new DbInfo();
           person.setId(cursor.getInt(cursor.getColumnIndex(Constant._ID)));
           person.setName(cursor.getString(cursor.getColumnIndex(Constant.NAME)));
            person.setAge(cursor.getInt(cursor.getColumnIndex(Constant.AGE)));
            list.add(person);
        }
        return list;
    }

    public static Cursor selectDataBySql(SQLiteDatabase db, String select_sql,String[] selectionArgs) {
        Cursor cursor=null;
        if (db!=null){
            cursor=db.rawQuery(select_sql,selectionArgs);
        }
        return cursor;
    }
}
