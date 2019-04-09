package com.example.ahao.myapplication.temp;

import android.app.Application;
import android.graphics.Paint;

/**
 * Created by Ahao on 2019/3/4
 */
public class MyConfig extends Application {
    private int width;
    private int height;
    private int span;
    private static MyConfig myApplication;
    private int manyRow;
    private Paint paint;

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getManyRow() {
        return manyRow;
    }

    public void setManyRow(int manyRow) {
        this.manyRow = manyRow;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyConfig getInstance(){
        return myApplication;
    }

}
