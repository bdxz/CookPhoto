package com.example.morphtin.dishes.ui.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.example.morphtin.dishes.ui.view.BottomBar;
import com.example.morphtin.dishes.util.RetrofitApi;
import com.example.morphtin.dishes.util.StartBrotherEvent;
import com.werb.pickphotoview.PickPhotoView;
import com.werb.pickphotoview.util.PickConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                new CustomPopupWindow(getContext()).showAtLocation(mCenterImage, Gravity.NO_GRAVITY, 0, 0);
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

    /**
     * Created by elevation on 18-4-9.
     */
    public class CustomPopupWindow extends PopupWindow {
        private static final String TAG = "CustomPopupWindow";

        private final int top;
        private final int bottom;
        private float animatorProperty[] = null;

        @BindView(R.id.pop_rl_click)
        RelativeLayout rlClick;
        @BindView(R.id.pop_iv_img)
        ImageView ivBtn;
        @BindView(R.id.test1)
        LinearLayout llTest1;
        @BindView(R.id.test2)
        LinearLayout llTest2;
        @BindView(R.id.test3)
        LinearLayout llTest3;
        @BindView(R.id.test4)
        LinearLayout llTest4;

        public CustomPopupWindow(final Context context) {
            super(context);

            View view = LayoutInflater.from(context).inflate(R.layout.popup_menu, null);

            setContentView(view);
            setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            setFocusable(false);

            // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
            //setClippingEnabled(false);
            setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setOutsideTouchable(true);

            top = dip2px(context, 310);
            bottom = dip2px(context, 210);
            animatorProperty = new float[]{bottom, 60, -30, -20 - 10, 0};

            ButterKnife.bind(this,view);

            rlClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    close();
                }
            });

            llTest1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PickPhotoView.Builder(_mActivity)
                            .setPickPhotoSize(6)
                            .setShowCamera(true)
                            .setSpanCount(4)
                            .setLightStatusBar(false)
                            .setStatusBarColor(R.color.blue_primary_dark)
                            .setToolbarColor(R.color.blue_primary)
                            .setToolbarTextColor(R.color.white)
                            .setSelectIconColor(R.color.blue_primary)
                            .start();
                }
            });
        }


        /**
         * dp转化为px
         *
         * @param context  context
         * @param dipValue dp value
         * @return 转换之后的px值
         */
        private int dip2px(Context context, float dipValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }

        @Override
        public void showAtLocation(View parent, int gravity, int x, int y) {
            super.showAtLocation(parent, gravity, x, y);
            openAnimation();
        }

        private void openAnimation() {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 0f, 135f);
            objectAnimator.setDuration(200);
            objectAnimator.start();

            translation(llTest1, 500, animatorProperty);
            translation(llTest2, 430, animatorProperty);
            translation(llTest3, 430, animatorProperty);
            translation(llTest4, 500, animatorProperty);
        }

        private void close() {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 135f, 0f);
            objectAnimator.setDuration(200);
            objectAnimator.start();

            translation(llTest1, 300, top);
            translation(llTest2, 200, top);
            translation(llTest3, 200, top);
            translation(llTest4, 300, top);

            rlClick.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, 300);
        }

        private void translation(View view, int duration, float[] distance) {
            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
            anim.setDuration(duration);
            anim.start();
        }

        private void translation(View view, int duration, int distance) {
            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
            anim.setDuration(duration);
            anim.start();
        }
    }
}
