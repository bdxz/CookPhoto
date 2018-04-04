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

public class DiscoveryFragment extends BaseFragment {

    public static DiscoveryFragment newInstance() {
        Bundle bundle = new Bundle();

        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        return view;
    }
}
