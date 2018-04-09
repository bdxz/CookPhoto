package com.example.morphtin.dishes.ui.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.example.morphtin.dishes.ui.view.BottomBar;
import com.example.morphtin.dishes.ui.view.CustomPopupWindow;
import com.example.morphtin.dishes.util.StartBrotherEvent;
import com.werb.pickphotoview.PickPhotoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by elevation on 18-4-4.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.center_img)
    ImageView mCenterImage;
    SupportFragment[] mFragments = new SupportFragment[5];

    private int mSelectPosition, mCurrentPosition = 0;

    public static MainFragment newInstance() {

        Bundle bundle = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void initEvents() {
        mBottomBar.setOnBottombarOnclick(new BottomBar.OnBottonbarClick() {

            @Override
            public void onFirstClick() {
                mSelectPosition = 0;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 0;
            }

            @Override
            public void onSecondClick() {
                mSelectPosition = 1;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 1;
            }

            @Override
            public void onThirdClick() {
                mSelectPosition = 2;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 2;
            }

            @Override
            public void onFouthClick() {
                mSelectPosition = 3;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 3;
            }

            @Override
            public void onCenterClick() {
                PickPhotoView.Builder builder = new PickPhotoView.Builder(_mActivity)
                        .setPickPhotoSize(6)
                        .setShowCamera(true)
                        .setSpanCount(4)
                        .setLightStatusBar(false);
                CustomPopupWindow customPopupWindow = new CustomPopupWindow(getContext());
                customPopupWindow.showAtLocation(mCenterImage, Gravity.NO_GRAVITY, 0, 0);
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData(boolean isSavedNull) {
        if (isSavedNull) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = DiscoveryFragment.newInstance();
            mFragments[2] = MessageFragment.newInstance();
            mFragments[3] = MineFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_tab_container, 0, mFragments[0], mFragments[1], mFragments[2], mFragments[3]);
        } else {
            mFragments[0] = findChildFragment(HomeFragment.class);
            mFragments[1] = findChildFragment(DiscoveryFragment.class);
            mFragments[2] = findChildFragment(MessageFragment.class);
            mFragments[3] = findChildFragment(MineFragment.class);
        }
    }

    /**
     * start other BrotherFragment
     */
    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
