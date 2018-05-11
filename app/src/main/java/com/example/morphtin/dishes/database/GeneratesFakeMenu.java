package com.example.morphtin.dishes.database;

import com.example.morphtin.dishes.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/11/011.
 */

public class GeneratesFakeMenu {
    private ArrayList<com.example.morphtin.dishes.bean.Menu_in_List> dataObjects = new ArrayList<>();


    public GeneratesFakeMenu() {
    }

    private ArrayList<String> titles = new ArrayList<String>() {
        {
            add("清甜梨耳露");
            add("美颜银耳羹");
            add("响油芦笋");
            add("茄汁汤面");
        }
    };
    private ArrayList<String> descriptions = new ArrayList<String>() {
        {
            add("母亲节，做一道营养饮品给妈妈！");
            add("丰富的膳食纤维，香甜软糯的口感！");
            add("营养丰富的芦笋是晚餐中很好的蔬菜选择！");
            add("暖暖的酸甜茄汁汤面，也可以让夜宵吃出暖意！");
        }
    };

    private ArrayList<Integer> photos = new ArrayList<Integer>() {
        {
            add(R.drawable.cookbook1);
            add(R.drawable.cookbook2);
            add(R.drawable.cookbook3);
            add(R.drawable.cookbook4);
        }
    };

    public ArrayList<com.example.morphtin.dishes.bean.Menu_in_List> generateDataFake() {
        for (int i = 0; i < 4; i++) {
            com.example.morphtin.dishes.bean.Menu_in_List dataObject = new com.example.morphtin.dishes.bean.Menu_in_List();
            dataObject.setId(i);
            dataObject.setTitle(titles.get(i));
            dataObject.setDescription(descriptions.get(i));
            dataObject.setPhoto(photos.get(i));
            dataObjects.add(dataObject);
        }
        return dataObjects;
    }
}
