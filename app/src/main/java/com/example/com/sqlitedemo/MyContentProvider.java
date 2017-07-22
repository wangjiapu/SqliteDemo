package com.example.com.sqlitedemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.com.sqlitedemo.utils.Constant;

/**
 * Created by 蒲家旺 on 2017/7/21.
 * Contenprovider的使用
 */

public class MyContentProvider extends ContentProvider {
    //Uri过滤器，通过此过滤器可以取出uri最后一个字段
    UriMatcher um;
    static final String AUTHORITY = "com.example.com.sqlitedemo";
    @Override
    public boolean onCreate() {

        um=new UriMatcher(UriMatcher.NO_MATCH);//初始化过滤器

        um.addURI(AUTHORITY, Constant.TABLE_NAME,1);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db=getContext().openOrCreateDatabase(Constant.DATABASE_NAME,
                Context.MODE_PRIVATE,null);
        Cursor cursor=null;
        //将uri过滤，取得返回值
        int code=um.match(uri);
        switch (code){
            case 1:
                cursor=db.rawQuery("select * from person",null);
                break;
        }
        //db.close();
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
