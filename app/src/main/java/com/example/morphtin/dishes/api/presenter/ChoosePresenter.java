package com.example.morphtin.dishes.api.presenter;

import android.util.Log;

import com.example.morphtin.dishes.api.contract.ChooseContract;
import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.api.model.impl.MaterialModelImpl;
import com.example.morphtin.dishes.api.model.mock.FakeMaterialModel;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.common.Constant;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class ChoosePresenter implements ChooseContract.Presenter {
    private static final String TAG = "ChoosePresenter";
    private IMaterialModel mMaterialModel;
    private ChooseContract.View mChooseView;

    public ChoosePresenter(ChooseContract.View view) {
        this.mChooseView = view;
        if(Constant.MOCK){
            mMaterialModel = FakeMaterialModel.getInstance();
        }else{
            mMaterialModel = MaterialModelImpl.getInstance();
        }
    }

    @Override
    public void loadAllMaterials() {
        mMaterialModel.getMaterials().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<MaterialBean>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
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
        Log.d(TAG, "selectByPhoto: "+photoPaths.size());
        mMaterialModel.getMaterials(photoPaths).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<MaterialBean>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(List<MaterialBean> materialBeans) {
                Log.d(TAG, "onNext: ");
                mChooseView.showLoading();
                mMaterialModel.setSelected(materialBeans);
                mChooseView.cancelLoading();
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
