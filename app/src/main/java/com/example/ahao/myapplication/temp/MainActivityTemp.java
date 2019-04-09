package com.example.ahao.myapplication.temp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.ahao.myapplication.Book;
import com.example.ahao.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ahao on 2019/3/3
 */
public class MainActivityTemp extends FragmentActivity {
    private ViewPager viewPager;
    private String txt;
    private int pages=10;
    private HashMap<Integer,Integer> hashMap;
    private List<ReaderFragment> readerFragments;
    private int nowShow=0;
    private MyViewPagerAdapter myViewPagerAdapter;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temp);
        Intent intentTemp = getIntent();
        Book book = (Book) intentTemp.getSerializableExtra(InitReader.bookTemp);

        UrlGetTask task=new UrlGetTask(this);
//        task.setUrl("http://101.132.156.198:8080/Novel/GetNovelContent?novelname=睡前故事&partname=儿童睡前故事");
        task.setUrl("http://101.132.156.198:8080/Novel/GetNovelContent?novelname="+book.getName()+"&partname="+book.getPartName());
        task.execute();
        task.setmSuccess(new UrlGetTask.onSuccessing() {
            @Override
            public void success(String s) {
                title=UrlGetTask.jsonTwoForOne(s,"title");
                txt=UrlGetTask.jsonTwoForOne(s,"data");
                doNext();
            }
        });

    }
    private void doNext() {
        viewPager=findViewById(R.id.vp);
        hashMap=new HashMap<>();
        pages=Tools.getPages(txt);
        readerFragments=new ArrayList<>();
        for (int i=0;i<pages;i++)
            readerFragments.add(ReaderFragment.newInstance(i));
        myViewPagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),readerFragments);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                float per=position==0?0:((float)hashMap.get(position-1))/txt.length();
                if(position!=0)
                ((ReaderFragment)readerFragments.get(position)).setReaderTxt(title,nowShow+2+"/"+pages);
                nowShow=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public String getTxt(int pos)
    {
        String needtxt=txt;
        if(pos!=0&&hashMap.get(pos-1)!=null)
        {
           needtxt=needtxt.substring(hashMap.get(pos-1));
        }
        return needtxt;
    }

    public void setTxt(int pos,int showat)
    {
        if(pos==nowShow)
        {
            if(pos==0)
                hashMap.put(pos,showat);
            else
                hashMap.put(pos,showat+hashMap.get(pos-1));
        }
    }

    public void initFirstPage()
    {
        ((ReaderFragment)readerFragments.get(0)).setReaderTxt(title,"1/"+pages);
    }


}
