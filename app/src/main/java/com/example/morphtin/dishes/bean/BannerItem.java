package com.example.morphtin.dishes.bean;

import java.util.UUID;

/**
 * Created by Morphtin on 2018/4/27.
 */

public class BannerItem {
    private String imageUrl;
    private String link;
    private String menu_id;


    public BannerItem(String imageUrl,String link,String menu_id){
        this.imageUrl = imageUrl;
        this.link = link;
        this.menu_id = menu_id;
    }


    public String getImageUrl(){
        return imageUrl;
    }
    public String getLink(){
        return link;
    }

    public String getMenu_id() {
        return menu_id;
    }
}
