package com.example.morphtin.dishes.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-5-4.
 */

public class MenuBean implements Parcelable {
    private String title;
    private String imageTitle;
    private ArrayList<MenuStep> steps;

    public MenuBean(){

    }


    public String getTitle(){
        return title;
    }

    public String getImage(){
        return imageTitle;
    }

    public ArrayList<MenuStep> getSteps(){
        return steps;
    }


    public void setTitle(String title){
        this.title = title;
    }
    public void setImageTitle(String imageTitle){
        this.imageTitle = imageTitle;
    }
    public void setSteps(ArrayList<MenuStep> steps){
        this.steps = steps;
    }


    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Storing the Company data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imageTitle);
//        MenuStep[] stepsArray = new MenuStep[steps.size()];
//        steps.toArray(stepsArray);
//        dest.writeParcelableArray(stepsArray,1);
        dest.writeList(steps);
    }


    private MenuBean(Parcel in){
        this.title = in.readString();
        this.imageTitle = in.readString();
//        MenuStep[] stepsArray = (MenuStep[])in.readParcelableArray(com.example.morphtin.dishes.bean.MenuStep.class.getClassLoader());
//        for(int i = 0;i<stepsArray.length;i++) {
//            this.steps.add(stepsArray[i]);
//        }
        ArrayList<MenuStep> s = new ArrayList<>();
        s=in.readArrayList(MenuStep.class.getClassLoader());
        this.steps = s;
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
