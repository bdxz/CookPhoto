package com.example.morphtin.dishes.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elevation on 18-4-26.
 */

public class MaterialBean implements Parcelable{
    private String title;
    private String catelog;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.catelog);
    }

    public MaterialBean() {
    }

    protected MaterialBean(Parcel in) {
        this.title = in.readString();
        this.catelog = in.readString();
    }

    public static final Creator<MaterialBean> CREATOR = new Creator<MaterialBean>() {
        @Override
        public MaterialBean createFromParcel(Parcel source) {
            return new MaterialBean(source);
        }

        @Override
        public MaterialBean[] newArray(int size) {
            return new MaterialBean[size];
        }
    };
}
