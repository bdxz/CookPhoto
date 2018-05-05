package com.example.morphtin.dishes.bean;

import java.util.List;

/**
 * Created by elevation on 18-5-4.
 */

public class MenuBean {
    private String title;
    private List<Step> steps;

    private class Step {
        private String desc;
        private String imageUrl;
    }
}
