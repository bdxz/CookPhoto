package com.example.morphtin.dishes.api.presenter.impl;

import android.util.Log;

import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.api.model.impl.MaterialModelImpl;
import com.example.morphtin.dishes.api.presenter.IMaterialPresenter;
import com.example.morphtin.dishes.api.view.IMaterialView;
import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by elevation on 18-4-25.
 */

public class MaterialPresenterImpl implements IMaterialPresenter{
    private static final String TAG = "MaterialPresenterImpl";
    private IMaterialModel mMaterialModel;
    private IMaterialView mMaterialView;

    public MaterialPresenterImpl(IMaterialView view) {
        this.mMaterialView = view;
        this.mMaterialModel=new MaterialModelImpl();
    }

    @Override
    public void loadMaterials(ArrayList<String> photoPaths) {
        mMaterialView.showProgress();
        mMaterialModel.loadMaterialList(photoPaths, new Observer<List<MaterialBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MaterialBean> materialBeans) {
                mMaterialView.hideProgress();
                mMaterialView.updateView(materialBeans);
            }

            @Override
            public void onError(Throwable e) {
                mMaterialView.hideProgress();
                mMaterialView.showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadMaterials() {
        mMaterialView.showProgress();
        mMaterialModel.loadMaterialList(new Observer<List<MaterialBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MaterialBean> materialBeans) {
                mMaterialView.hideProgress();
                mMaterialView.updateView(materialBeans);
            }

            @Override
            public void onError(Throwable e) {
                mMaterialView.hideProgress();
                mMaterialView.showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
