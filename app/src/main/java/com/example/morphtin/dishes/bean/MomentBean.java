package com.example.morphtin.dishes.bean;

import java.util.List;

public class MomentBean {
    private Integer momentID;
    private SimpleUserBean simpleUserBean;
    private String  content;
    private List<String> imageUrls;

    public Integer getMomentID() {
        return momentID;
    }

    public void setMomentID(Integer momentID) {
        this.momentID = momentID;
    }

    public SimpleUserBean getSimpleUserBean() {
        return simpleUserBean;
    }

    public void setSimpleUserBean(SimpleUserBean simpleUserBean) {
        this.simpleUserBean = simpleUserBean;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
