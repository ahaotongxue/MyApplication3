package com.example.ahao.myapplication.temp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.ahao.myapplication.Book;
import com.example.ahao.myapplication.R;
import com.example.ahao.myapplication.ReadListViewActivity;

/**
 * Created by Ahao on 2019/3/4
 */
public class InitReader extends Activity {
    private ReaderView readerView;
    public static String bookTemp = "bookTemp";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_reader_layout);
        readerView=findViewById(R.id.reader);
        Intent tempIntent = getIntent();
        final Book book = (Book) tempIntent.getSerializableExtra(ReadListViewActivity.bookTag);
        readerView.post(new Runnable() {
            @Override
            public void run() {
                MyConfig.getInstance().setHeight(readerView.getHeight());
                MyConfig.getInstance().setWidth(readerView.getWidth());
                MyConfig.getInstance().setSpan(readerView.getRowHeight());
                MyConfig.getInstance().setManyRow(readerView.getHeight()/readerView.getRowHeight());
                MyConfig.getInstance().setPaint(readerView.getPaint());

                Intent intent=new Intent(InitReader.this,MainActivityTemp.class);
                intent.putExtra(bookTemp,book);
                startActivity(intent);
                finish();
            }
        });
    }
}
