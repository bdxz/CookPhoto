package com.example.morphtin.dishes.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.base.BaseFragment;

/**
 * Created by elevation on 18-4-4.
 */

public class MineFragment extends BaseFragment {

    public static MineFragment newInstance() {
        Bundle bundle = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }
}
