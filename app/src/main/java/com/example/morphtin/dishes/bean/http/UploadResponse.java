package com.example.morphtin.dishes.bean.http;

import java.util.List;

/**
 * Created by elevation on 18-5-4.
 */

public class UploadResponse extends BaseResponse{
    private List<String> imageUrl;

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
