package com.example.morphtin.dishes.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.ChooseContract;
import com.example.morphtin.dishes.api.contract.MaterialContract;
import com.example.morphtin.dishes.api.presenter.ChoosePresenter;
import com.example.morphtin.dishes.api.presenter.MaterialPresenter;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.ui.adapter.PhotoAdapter;
import com.example.morphtin.dishes.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elevation on 18-4-25.
 */

public class ChoosePhotoActivity extends BaseActivity{
    private PhotoAdapter adapter;
    private ArrayList<String> photoPaths;
    private ChooseContract.Presenter presenter;

    @BindView(R.id.photo_list)
    RecyclerView photoList;
    @BindView(R.id.upload_photo)
    ActionProcessButton mProcessButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_photo);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        photoPaths = getIntent().getStringArrayListExtra("photoPaths");
        adapter = new PhotoAdapter(this, photoPaths);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        photoList.setLayoutManager(layoutManager);
        photoList.setAdapter(adapter);

        adapter.updateData(photoPaths);

        presenter = new ChoosePresenter(null);
    }

    @Override
    protected void initEvents() {
        mProcessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectByPhoto(photoPaths);
            }
        });
    }
}
