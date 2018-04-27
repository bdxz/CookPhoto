package com.example.morphtin.dishes.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.User;
import com.example.morphtin.dishes.ui.base.BaseFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by elevation on 18-4-4.
 */

public class MineFragment extends BaseFragment {

    private CircleImageView mCircleImageView;
    private User user;
    TextView textViewName;


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
        mCircleImageView = (CircleImageView) view.findViewById(R.id.profile_image);
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleImageView.setImageResource(R.drawable.vegetable);
            }
        });
        user = User.getUser(getActivity()).getUserInf("昵称");
        textViewName = view.findViewById(R.id.profile_name);
        setProfile();
        return view;
    }

    public void setProfile(){
        textViewName.setText(user.getName());
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //mRootView = inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }



}
