package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.MaterialContract;
import com.example.morphtin.dishes.api.model.IMatchModel;
import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.MatchModelImpl;
import com.example.morphtin.dishes.api.model.impl.MaterialModelImpl;
import com.example.morphtin.dishes.api.model.impl.MenuModelImpl;
import com.example.morphtin.dishes.api.model.mock.FakeMatchModel;
import com.example.morphtin.dishes.api.model.mock.FakeMaterialModel;
import com.example.morphtin.dishes.api.model.mock.FakeMenuModel;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.common.Constant;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by elevation on 18-5-14.
 */

public class MaterialPresenter implements MaterialContract.Presenter {
    private IMaterialModel mMaterialModel;
    private IMenuModel mMenuModel;
    private IMatchModel mMatchModel;
    private MaterialContract.View mMaterialView;

    public MaterialPresenter(MaterialContract.View view) {
        this.mMaterialView = view;
        if(Constant.MOCK){
            mMaterialModel = FakeMaterialModel.getInstance();
            mMenuModel = FakeMenuModel.getInstance();
            mMatchModel = FakeMatchModel.getInstance();
        }else{
            mMaterialModel = MaterialModelImpl.getInstance();
            mMenuModel = MenuModelImpl.getInstance();
            mMatchModel = MatchModelImpl.getInstance();
        }
    }

    @Override
    public void loadSelected() {
        mMaterialModel.getSelected().observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<MaterialBean>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(List<MaterialBean> materialBeans) {
                mMaterialView.showMaterials(materialBeans);
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
    public void matchMenus(List<MaterialBean> data) {
        mMatchModel.match(data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MenuBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MenuBean> menuBeans) {
                mMatchModel.setMatch(menuBeans);
                mMaterialView.showMenusUI();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void unselect(MaterialBean data) {
        mMaterialModel.setUnselect(data);
    }
}
