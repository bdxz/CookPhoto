package com.example.morphtin.dishes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

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

public class ChoosePhotoActivity extends BaseActivity implements ChooseContract.View{
    private PhotoAdapter adapter;
    private ArrayList<String> photoPaths;
    private ChooseContract.Presenter presenter;
    final PopupWindow popupWindow = new PopupWindow();
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

        presenter = new ChoosePresenter(this);
    }

    @Override
    public void showLoading() {

        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        View load_view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.loading,null);
        popupWindow.setContentView(load_view);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER,0,0);
    }

    @Override
    public void cancelLoading() {
        popupWindow.dismiss();
    }

    @Override
    protected void initEvents() {
        mProcessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectByPhoto(photoPaths);
                Toast.makeText(getApplicationContext(), "正在加载", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showMaterials(List<MaterialBean> data) {

    }

    @Override
    public void showSelected(List<MaterialBean> data) {
        Intent intent = new Intent(this,MaterialListActivity.class);
        startActivity(intent);
    }
}
