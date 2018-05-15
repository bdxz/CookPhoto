package com.example.morphtin.dishes.api.presenter;

import com.example.morphtin.dishes.api.contract.MaterialContract;
import com.example.morphtin.dishes.api.model.IMatchModel;
import com.example.morphtin.dishes.api.model.IMaterialModel;
import com.example.morphtin.dishes.api.model.IMenuModel;
import com.example.morphtin.dishes.api.model.impl.MatchModelImpl;
import com.example.morphtin.dishes.api.model.impl.MaterialModelImpl;
import com.example.morphtin.dishes.api.model.impl.MenuModelImpl;
import com.example.morphtin.dishes.bean.MaterialBean;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
        mMaterialModel = MaterialModelImpl.getInstance();
        mMenuModel = MenuModelImpl.getInstance();
        mMatchModel = MatchModelImpl.getInstance();
    }

    @Override
    public void loadSelected() {
        mMaterialModel.getSelected().observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<MaterialBean>>() {
            @Override
            public void onSubscribe(Subscription s) {

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
        mMatchModel.match(data);
        mMaterialView.showMenusUI();
    }

    @Override
    public void unselect(MaterialBean data) {
        mMaterialModel.setUnselect(data);
    }
}
