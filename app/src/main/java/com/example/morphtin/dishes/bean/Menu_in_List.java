package com.example.morphtin.dishes.bean;

/**
 * Created by Administrator on 2018/5/11/011.
 */

public class Menu_in_List {
    private int id;
    private String title;
    private String description;
    private int photo;
    private boolean isNew;
    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
