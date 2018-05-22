package com.example.morphtin.dishes.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-5-4.
 */

public class MenuBean implements Parcelable {
    private String menu_id;
    private String title;
    private String imageUrl;
    private ArrayList<MenuStep> steps;

    public MenuBean(){

    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<MenuStep> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<MenuStep> steps) {
        this.steps = steps;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.menu_id);
        dest.writeString(this.title);
        dest.writeString(this.imageUrl);
        dest.writeTypedList(this.steps);
    }

    protected MenuBean(Parcel in) {
        this.menu_id = in.readString();
        this.title = in.readString();
        this.imageUrl = in.readString();
        this.steps = in.createTypedArrayList(MenuStep.CREATOR);
    }

    public static final Creator<MenuBean> CREATOR = new Creator<MenuBean>() {
        @Override
        public MenuBean createFromParcel(Parcel source) {
            return new MenuBean(source);
        }

        @Override
        public MenuBean[] newArray(int size) {
            return new MenuBean[size];
        }
    };
}
