package com.example.morphtin.dishes.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.view.IMineView;
import com.example.morphtin.dishes.bean.User;
import com.example.morphtin.dishes.bean.UserInfoBean;
import com.example.morphtin.dishes.ui.base.BaseFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by elevation on 18-4-4.
 */

public class MineFragment extends BaseFragment implements IMineView {

    private CircleImageView mCircleImageView;
    private User user;
    private TextView textViewName;
    private LinearLayout profileLayout;


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
        textViewName = view.findViewById(R.id.profile_name);
        profileLayout = view.findViewById(R.id.profile_layout);
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.getUser(getActivity()).updateUser("昵称",new User("update","昵称","update","null"));
                setProfile("昵称");
            }
        });
        setProfile("昵称");
        return view;
    }

    public void setProfile(String name){
        user = User.getUser(getActivity()).getUserInf(name);
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


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateView(UserInfoBean data) {

    }

    @Override
    public void showMessage(String msg) {

    }
}
