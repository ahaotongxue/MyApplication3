package com.example.ahao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.ahao.myapplication.temp.InitReader;

import java.util.ArrayList;
import java.util.List;

public class ReadListViewActivity extends AppCompatActivity {
    private List <Book> bookList=new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    public static String bookTag = "bookData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_list_view);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        final NavigationView navView=findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.user);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_like:
                        Intent intent =new Intent(ReadListViewActivity.this,LikeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_local:
                        Intent intent1=new Intent(ReadListViewActivity.this,LocalActivity.class);
                        startActivity(intent1);
                        break;
                }
                return true;
            }
        });
        initBooks();
        BookAdapter adapter=new BookAdapter(ReadListViewActivity.this,R.layout.book_item,bookList);
        final ListView listView=findViewById(R.id.list_read);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book=bookList.get(position);
                Intent intent = new Intent(ReadListViewActivity.this,InitReader.class);
                intent.putExtra(bookTag,book);
                startActivity(intent);
            }
        });
    }

    //http://101.132.156.198:8080/Novel/GetNovelContent?novelname=睡前故事&partname=儿童睡前故事"
    private void initBooks() {
            Book tonghua= new Book("童话故事",R.drawable.bookbook,"格林童话");
            bookList.add(tonghua);
            Book sleep= new Book("睡前故事",R.drawable.bookbook,"儿童睡前故事");
            bookList.add(sleep);
            Book gudai= new Book("中国古代故事",R.drawable.bookbook,"中国古代故事");
            bookList.add(gudai);
            Book tangshi= new Book("唐诗",R.drawable.bookbook,"唐诗三百首");
            bookList.add(tangshi);
            Book b1= new Book("三寸人间",R.drawable.bookbook,"第一章-我要减肥！");
            bookList.add(b1);
            Book b2= new Book("三寸人间",R.drawable.bookbook,"第二章-好同学，一切有我！");
            bookList.add(b2);
            Book b3= new Book("武帝",R.drawable.bookbook,"吞日！十日归一！");
            bookList.add(b3);
            Book b4= new Book("XXXXXX",R.drawable.bookbook,"");
            bookList.add(b4);
            Book b5= new Book("XXXXXXX",R.drawable.bookbook,"");
            bookList.add(b5);
            Book b6= new Book("XXXXXXXX",R.drawable.bookbook,"");
            bookList.add(b6);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
                default:
        }
        return true;
    }
}
