package com.example.ahao.myapplication;

import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private int imageId;
    private String partname;

    //novelname=睡前故事&partname=儿童睡前故事"

    public Book(String name,int imageId,String partname){
        this.name=name;
        this.imageId=imageId;
        this.partname = partname;
    }
    public String getName(){
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPartName(){
        return partname;
    }

}
