package com.example.morphtin.dishes.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;


/**
 * Created by Administrator on 2018/6/5 0005.
 */

public class Message {
    private int imageID;
    private String title;
    private String dscp;
    private String time;
    private List<Message> message;

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public Message(int imageID, String title, String description, String time){
        this.imageID = imageID;
        this.title = title;

        this.dscp = description;
        this.time = time;
    }

    public Message(){

    }



    public String getDscp() {
        return dscp;
    }

    public void setDscp(String dscp) {
        this.dscp = dscp;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
