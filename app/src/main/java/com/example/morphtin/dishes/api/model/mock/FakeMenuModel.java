package com.example.morphtin.dishes.api.model.mock;

import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.MenuStep;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by elevation on 18-5-21.
 */

public class FakeMenuModel implements IMenuModel {
    private static final FakeMenuModel ourInstance = new FakeMenuModel();

    public static FakeMenuModel getInstance() {
        return ourInstance;
    }

    private FakeMenuModel() {
    }

    @Override
    public Flowable<List<MenuBean>> getBannerMenus() {
        List<MenuBean> data = new ArrayList<>();
        MenuBean menu = new MenuBean();
        menu.setMenu_id("1");
        menu.setTitle("红烧茄子");
        menu.setImageUrl("https://i8.meishichina.com/attachment/recipe/2015/04/23/20150423445baeb70fbbbf3e.jpg@!p800");
        ArrayList<MenuStep> steps = new ArrayList<>();
        steps.add(new MenuStep("https://i8.meishichina.com/attachment/recipe/2015/04/23/p800_20150423680273fdc96a0d5f.jpg@!p320","准备食材"));
        steps.add(new MenuStep("https://i8.meishichina.com/attachment/recipe/2015/04/23/p800_20150423c1c144f6513e0881.jpg@!p320","将茄子去皮切块、青辣椒切块、蒜切末备用"));
        menu.setSteps(steps);
        data.add(menu);
        return Flowable.just(data);
    }

    @Override
    public Flowable<MenuBean> getMenu(String menu_id) {
        MenuBean menu = new MenuBean();
        menu.setMenu_id("1");
        menu.setTitle("红烧茄子");
        menu.setImageUrl("https://i8.meishichina.com/attachment/recipe/2015/04/23/20150423445baeb70fbbbf3e.jpg@!p800");
        ArrayList<MenuStep> steps = new ArrayList<>();
        steps.add(new MenuStep("https://i8.meishichina.com/attachment/recipe/2015/04/23/p800_20150423680273fdc96a0d5f.jpg@!p320","准备食材"));
        steps.add(new MenuStep("https://i8.meishichina.com/attachment/recipe/2015/04/23/p800_20150423c1c144f6513e0881.jpg@!p320","将茄子去皮切块、青辣椒切块、蒜切末备用"));
        menu.setSteps(steps);
        return Flowable.just(menu);
    }

    @Override
    public void saveMenu(MenuBean menu) {

    }


    @Override
    public Flowable<MenuBean> downloadMenu(String menu_id) {
        return null;
    }
}
