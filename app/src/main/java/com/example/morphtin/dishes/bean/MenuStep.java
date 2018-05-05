package com.example.morphtin.dishes.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.reactivex.internal.operators.maybe.MaybeNever;

/**
 * Created by Morphtin on 2018/5/5.
 */

public class MenuStep implements Parcelable {
    private String imageUrl;
    private String description;


    public MenuStep(String imageUrl, String description){
        this.imageUrl = imageUrl;
        this.description = description;
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
        dest.writeString(imageUrl);
        dest.writeString(description);
    }


    private MenuStep(Parcel in){
        this.imageUrl = in.readString();
        this.description = in.readString();
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
        return description;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
