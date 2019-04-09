package com.example.ahao.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter <Book>{

    private int resourceId;

    public BookAdapter(Context context, int textVierReourceId, List<Book> objects){
        super(context,textVierReourceId,objects);
        resourceId =textVierReourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Book book=getItem(position);//获取当前项的BOOK实例
        View view;
        ViewHolder viewHolder;
        if(convertView ==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder =new ViewHolder();
            viewHolder.bookImage=view.findViewById(R.id.book_image);
            viewHolder.bookName=view.findViewById(R.id.book_name);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        //ImageView bookImage = view.findViewById(R.id.book_image);
        //TextView bookName=view.findViewById(R.id.book_name);
        //bookImage.setImageResource(book.getImageId());
        //bookName.setText(book.getName());
        viewHolder.bookImage.setImageResource(book.getImageId());
        viewHolder.bookName.setText(book.getName());
        return view;
    }
    class ViewHolder{
        ImageView bookImage;
        TextView bookName;
    }
}
