package com.example.morphtin.dishes.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elevation on 18-4-26.
 */

public class MaterialBean implements Parcelable{
    private String title;
    private String catelog;
    private boolean status;

    public MaterialBean(){

    }

    public boolean getStatus(){return status;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatelog() {
        return catelog;
    }

    public void setCatelog(String catelog) {
        this.catelog = catelog;
    }

    public void setStatus(boolean status){this.status = status;}

    public static Creator<MaterialBean> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.catelog);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialBean that = (MaterialBean) o;

        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
