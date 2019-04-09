package com.example.ahao.myapplication.temp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Ahao on 2019/3/4
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    List<ReaderFragment> data;

    public MyViewPagerAdapter(FragmentManager fm, List<ReaderFragment> data)
    {
        super(fm);
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public List<ReaderFragment> getData() {
        return data;
    }

    public void setData(List<ReaderFragment> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public void addData(ReaderFragment readerFragment)
    {
        data.add(readerFragment);
        this.notifyDataSetChanged();
    }
}
