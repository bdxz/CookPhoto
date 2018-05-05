package com.example.morphtin.dishes.bean;

/**
 * Created by Morphtin on 2018/5/5.
 */

public class MenuStep {
    private String imageUrl;
    private String description;
    private String title;


    public MenuStep(String imageUrl, String description,String title){
        this.imageUrl = imageUrl;
        this.description = description;
        this.title = title;
    }


    public String getImageUrl(){
        return imageUrl;
    }
    public String getDescription(){
        return description;
    }
    public String getTitle(){return title;}
}
