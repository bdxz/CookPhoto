package com.example.morphtin.dishes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.skyfishjy.library.RippleBackground;

import java.util.Timer;

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
        final RippleBackground rippleBackground = (RippleBackground) view.findViewById(R.id.processingBack);
               rippleBackground.startRippleAnimation();
        return view;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }
}
