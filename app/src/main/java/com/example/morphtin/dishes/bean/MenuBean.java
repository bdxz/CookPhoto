package com.example.morphtin.dishes.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-5-4.
 */

public class MenuBean {
    private String title;
    private String imageTitle;
    private List<MenuStep> steps;


    public void setTitle(String title){
        this.title = title;
    }
    public void setImageTitle(String imageTitle){
        this.imageTitle = imageTitle;
    }
    public void setSteps(List<MenuStep> steps){
        this.steps = steps;
    }
}
