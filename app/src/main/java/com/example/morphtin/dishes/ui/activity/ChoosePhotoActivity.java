package com.example.morphtin.dishes.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.ui.fragment.MainFragment;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by elevation on 18-4-25.
 */

public class ChoosePhotoActivity extends SupportActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);
    }
}
