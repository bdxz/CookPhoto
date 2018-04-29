package com.example.morphtin.dishes.bean;

/**
 * Created by Morphtin on 2018/4/27.
 */

public class BannerItem {
    private String imageUrl;
    private String link;


    public BannerItem(String imageUrl,String link){
        this.imageUrl = imageUrl;
        this.link = link;
    }


    public String getImageUrl(){
        return imageUrl;
    }
    public String getLink(){
        return link;
    }
}
