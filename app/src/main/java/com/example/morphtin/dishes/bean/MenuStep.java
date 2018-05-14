package com.example.morphtin.dishes.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.reactivex.internal.operators.maybe.MaybeNever;

/**
 * Created by Morphtin on 2018/5/5.
 */

public class MenuStep implements Parcelable {
    private String imageUrl;
    private String desc;


    public MenuStep(String imageUrl, String desc){
        this.imageUrl = imageUrl;
        this.desc = desc;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing the Company data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(desc);
    }


    private MenuStep(Parcel in){
        this.imageUrl = in.readString();
        this.desc = in.readString();
    }

    public static final Creator<MenuStep> CREATOR = new Creator<MenuStep>() {

        @Override
        public MenuStep createFromParcel(Parcel source) {
            return new MenuStep(source);
        }

        @Override
        public MenuStep[] newArray(int size) {
            return new MenuStep[size];
        }
    };


    public String getImageUrl(){
        return imageUrl;
    }
    public String getDescription(){
        return desc;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description){
        this.desc = description;
    }

}
