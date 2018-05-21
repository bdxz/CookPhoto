package com.example.morphtin.dishes.api.model;

/**
 * Created by lenovo on 2018/5/21.
 */

public class Trace {
    /** 描述 */
    private String description;
    /* 步骤图片的id*/
    private String picUrl;
    private String stepCountDesc;

    public Trace() {
    }

    public Trace(String stepCountDesc,String description,String picUrl) {
        this.stepCountDesc = stepCountDesc;
        this.picUrl = picUrl;
        this.description = description;
    }

    public String getStepCountDesc(){
        return stepCountDesc;
    }

    public void setStepCountDesc(String stepCountDesc){
        this.stepCountDesc = stepCountDesc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl){
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
