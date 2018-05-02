package com.example.morphtin.dishes.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.presenter.IMaterialPresenter;
import com.example.morphtin.dishes.api.presenter.impl.MaterialPresenterImpl;
import com.example.morphtin.dishes.api.view.IMaterialView;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by elevation on 18-4-26.
 */

public class MaterialListActivity extends BaseActivity implements IMaterialView {
    private static final String TAG = "MaterialListActivity";
    private IMaterialPresenter presenter;
    private ArrayList<String> photoPaths;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_material_list);
        ButterKnife.bind(this);
        presenter = new MaterialPresenterImpl(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        photoPaths = getIntent().getStringArrayListExtra("photoPaths");
    }

    @Override
    protected void initEvents() {
        presenter.loadMaterials(photoPaths);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateView(List<MaterialBean> data) {
        Log.d(TAG, "updateView: "+data.toString());
    }

    @Override
    public void showMessage(String msg) {

    }
}
