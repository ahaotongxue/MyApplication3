package com.example.ahao.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBNAME="book.db";
    private static final int VERSION=3; //设置版本号
    private static final String TBL_DETAILNEWS="collection"; //创建表名为news的表
    private static final String TBL_DETAILNEWS_COLUMN_TITLE="_title";
    private static final String TBL_DETAILNEWS_COLUMN_URL="_url";
    private static final String TBL_DETAILNEWS_COLUMN_DOCID="_docid";
    private static final String TBL_DETAILNEWS_COLUMN_STATE="_state";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb=new StringBuffer();
        sb.append("create table if not exists ");
        sb.append(TBL_DETAILNEWS+"(");
        sb.append(TBL_DETAILNEWS_COLUMN_DOCID +" varchar(100) primary key ,"); //设置主键
        sb.append(TBL_DETAILNEWS_COLUMN_TITLE+ " varchar(100) ,");
        sb.append(TBL_DETAILNEWS_COLUMN_URL+" varchar(100) ,");
        sb.append(TBL_DETAILNEWS_COLUMN_STATE+" integer ");
        sb.append(")");
        db.execSQL(sb.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql2="drop table if exists "+TBL_DETAILNEWS;
        db.execSQL(sql2); //创建
        onCreate(db);
    }
}