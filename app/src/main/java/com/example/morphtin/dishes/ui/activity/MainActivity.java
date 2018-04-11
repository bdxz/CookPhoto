package com.example.morphtin.dishes.ui.activity;

import android.os.Bundle;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.ui.fragment.MainFragment;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fragment_container, MainFragment.newInstance());
        }
    }
}
