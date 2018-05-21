package com.example.morphtin.dishes.api.model.mock;

import com.example.morphtin.dishes.api.model.IMatchModel;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by elevation on 18-5-21.
 */

public class FakeMatchModel implements IMatchModel {
    private static final FakeMatchModel ourInstance = new FakeMatchModel();

    public static FakeMatchModel getInstance() {
        return ourInstance;
    }

    private FakeMatchModel() {
    }

    @Override
    public void match(List<MaterialBean> data) {

    }

    @Override
    public Flowable<List<MenuBean>> get() {
        return null;
    }

    @Override
    public void clear() {

    }
}
