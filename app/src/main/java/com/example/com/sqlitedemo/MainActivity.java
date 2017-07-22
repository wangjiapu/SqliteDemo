package com.example.com.sqlitedemo;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.sqlitedemo.bean.DbInfo;
import com.example.com.sqlitedemo.utils.Constant;
import com.example.com.sqlitedemo.utils.DbManget;
import com.example.com.sqlitedemo.utils.MySqliteHelper;

import java.util.List;

import static com.example.com.sqlitedemo.LitePal_model.LpUtils.lpCreate_db;
import static com.example.com.sqlitedemo.LitePal_model.LpUtils.lpDel_db;
import static com.example.com.sqlitedemo.LitePal_model.LpUtils.lpInsert_db;
import static com.example.com.sqlitedemo.LitePal_model.LpUtils.lpShow_db;
import static com.example.com.sqlitedemo.LitePal_model.LpUtils.lpUpdate_db;


public class MainActivity extends AppCompatActivity
        implements Toolbar.OnMenuItemClickListener {
    private Toolbar tools;
    private MySqliteHelper helper;
    private SQLiteDatabase db = null;
    private TextView textView;
    private ListView lv;

    private Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = this;
        //初始化toolbar
        tools = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tools);
        tools.setOnMenuItemClickListener(this);
        //初始化数据库
        helper = DbManget.getIntance(this);

        textView = (TextView) findViewById(R.id.tv);
        lv = (ListView) findViewById(R.id.listview);

    }

    /**
     * 点击创建数据库
     * @param view view
     */
    public void createdp(View view) {
        /**
         *
         * 创建或者打开数据库
         * helper.getReadableDatabase();
         * helper.getWritableDatabase();
         * 默认都是可不可写的，磁盘满了的时候才会不可读，不可写等
         */
        db = helper.getWritableDatabase();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.in:
                /**
                 * 普通的通过sqlite 语句插入代码
                 */
                /*db=helper.getWritableDatabase();
                String sql="insert into "+ Constant.TABLE_NAME+" values(1,'旺哥',22)";
                DbManget.execSQL(db,sql);
                String sql2="insert into "+Constant.TABLE_NAME+" values(2,'呵呵',23)";
                DbManget.execSQL(db,sql2);
                db.close();
                Toast.makeText(MainActivity.this,"插入成功",Toast.LENGTH_SHORT).show();*/

                /**
                 * 通过SQLiteDataBase内置函数插入
                 *
                 */
                db = helper.getWritableDatabase();
                long resulty = 0;
                for (int i = 0; i < 10; i++) {
                    ContentValues values = new ContentValues();
                    values.put(Constant._ID, i);
                    values.put(Constant.NAME, "名字：" + i);
                    values.put(Constant.AGE, 20 + i);
                    if (db.insert(Constant.TABLE_NAME, null, values) > 0) {
                        resulty += 1;
                    }
                }
                if (resulty == 9) {
                    Toast.makeText(MainActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "插入失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.dele:
                db = helper.getWritableDatabase();

                 /* String sql4="delete from "+Constant.TABLE_NAME+" where "+Constant._ID+"=2";
                DbManget.execSQL(db,sql4);
                db.close();
                Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();*/

                int resultx = db.delete(Constant.TABLE_NAME, Constant._ID + "=?", new String[]{"3"});
                if (resultx > 0) {
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.updata:
                db = helper.getWritableDatabase();

               /* String sql3="update "+Constant.TABLE_NAME+" set "
                        +Constant.NAME+" ='xiaoming where "+Constant._ID+"=1'";
                DbManget.execSQL(db,sql3);
                db.close();
                Toast.makeText(MainActivity.this,"修成功",Toast.LENGTH_SHORT).show();*/

                ContentValues cv = new ContentValues();
                cv.put(Constant.NAME, "阿爸");
                int count = db.update(Constant.TABLE_NAME,
                        cv, Constant._ID + "=?", new String[]{"3"});
                if (count > 0) {
                    Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.sel:
                db = helper.getWritableDatabase();
                String select_sql = "select * from " + Constant.TABLE_NAME;
                List<DbInfo> list = DbManget.cursor2List(
                        DbManget.selectDataBySql(db, select_sql, null));
                StringBuffer sb = new StringBuffer();
                sb.append("结果为：\n");
                for (DbInfo d : list) {
                    sb.append(d.toString());
                }
                textView.setText(sb.toString());
                break;

            case R.id.list_menu:
                db = helper.getWritableDatabase();
                String ss = "select * from " + Constant.TABLE_NAME;
                Cursor cursor = DbManget.selectDataBySql(db, ss, null);
                /*String path= Environment.getExternalStorageDirectory()
                        .getAbsolutePath()+ File.pathSeparator+"info.db";
                db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
                Cursor cursor=db.rawQuery("select * from "+Constant.TABLE_NAME,null);*/

                //加载数据源
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        this, R.layout.list_item, cursor,
                        new String[]{Constant._ID, Constant.NAME, Constant.AGE},
                        new int[]{R.id.info_id, R.id.info_name, R.id.info_age},
                        SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                //主键必需要以下划线开始
                lv.setAdapter(adapter);
                break;
            case R.id.call:
                call_permissions();
                break;
            case R.id.lp_create:
                if (lpCreate_db()==null){
                    Toast.makeText(con,"lp-失败",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(con,"lp-成功",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lp_insert:
                lpInsert_db();
                break;
            case R.id.lp_del:
                lpDel_db();
                break;
            case R.id.lp_show:
                textView.setText(lpShow_db());
                break;
            case R.id.lp_update:
                lpUpdate_db();
                break;
        }
        return true;
    }

    /**
     * 打电话时的危险权限练习
     */
    private void call_permissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            call();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "你没有这个权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "你没有这个权限", Toast.LENGTH_SHORT).show();
            return;
        }
        con.startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }
}
