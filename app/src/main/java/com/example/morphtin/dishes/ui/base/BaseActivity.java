package com.example.morphtin.dishes.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by elevation on 18-4-25.
 */

public abstract class BaseActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initEvents();
    }


    protected void initData(){

    }

    protected abstract void initEvents();
}
