package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.ChooseContract;
import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.api.model.impl.MaterialModelImpl;
import com.example.morphtin.dishes.bean.MaterialBean;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class ChoosePresenter implements ChooseContract.Presenter {
    private IMaterialModel mMaterialModel;
    private ChooseContract.View mChooseView;

    public ChoosePresenter(ChooseContract.View view) {
        this.mChooseView = view;
        mMaterialModel = MaterialModelImpl.getInstance();
    }

    @Override
    public void loadAllMaterials() {
        mMaterialModel.getMaterials().observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<MaterialBean>>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(List<MaterialBean> materialBeans) {
                mChooseView.showMaterials(materialBeans);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void select(List<MaterialBean> data) {
        mMaterialModel.setSelected(data);
        mChooseView.showSelected(data);
    }

    @Override
    public void selectByPhoto(ArrayList<String> photoPaths) {
        mMaterialModel.getMaterials(photoPaths).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<MaterialBean>>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(List<MaterialBean> materialBeans) {
                mMaterialModel.setSelected(materialBeans);
                mChooseView.showSelected(materialBeans);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
