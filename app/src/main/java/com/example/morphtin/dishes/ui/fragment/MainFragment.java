package com.example.morphtin.dishes.ui.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.morphtin.dishes.BaseApplication;
import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.MenuStep;
import com.example.morphtin.dishes.ui.activity.MaterialListActivity;
import com.example.morphtin.dishes.ui.activity.MenuDetailActivity;
import com.example.morphtin.dishes.ui.activity.UploadMenuActivity;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.example.morphtin.dishes.ui.view.BottomBar;
import com.example.morphtin.dishes.util.StartBrotherEvent;
import com.werb.pickphotoview.PickPhotoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by elevation on 18-4-4.
 */

public class MainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.center_img)
    ImageView mCenterImage;
    static MainFragment mainFragment ;
    private Socket mSocket;
    private SharedPreferences sp ;
    SupportFragment supportFragment ;
    SupportFragment[] mFragments = new SupportFragment[5];

    private int mSelectPosition, mCurrentPosition = 0;

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "call: Connect success");
        }
    };

    public static MainFragment getInstance() {
        if(mainFragment == null) {
            Bundle bundle = new Bundle();

            mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            return mainFragment;
        }else{
            return mainFragment;
        }

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
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("首页");
            }

            @Override
            public void onSecondClick() {
                mSelectPosition = 1;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 1;
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("发现");
            }

            @Override
            public void onThirdClick() {
                mSelectPosition = 2;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 2;
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("消息");
            }

            @Override
            public void onFouthClick() {
                mSelectPosition = 3;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 3;
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("个人资料");
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
        sp = getContext().getSharedPreferences("user",Context.MODE_PRIVATE);
        String name = sp.getString("name","");
        Log.d(TAG, "initData: "+name);
        if(name != ""){
            supportFragment = MineFragment.newInstance();
        }else{
            supportFragment = LoginFragment.newInstance();
        }
        if (isSavedNull) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = DiscoveryFragment.newInstance();
            mFragments[2] = MessageFragment.newInstance();
            mFragments[3] = supportFragment;
            loadMultipleRootFragment(R.id.fl_tab_container, 0, mFragments[0], mFragments[1], mFragments[2], mFragments[3]);
        } else {
            mFragments[0] = findChildFragment(HomeFragment.class);
            mFragments[1] = findChildFragment(DiscoveryFragment.class);
            mFragments[2] = findChildFragment(MessageFragment.class);
            mFragments[3] = findChildFragment(supportFragment.getClass());
        }
        BaseApplication app = (BaseApplication) getActivity().getApplication();
        mSocket = app.getSocket();
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.connect();
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

        mSocket.disconnect();

        mSocket.off(Socket.EVENT_CONNECT, onConnect);

        EventBus.getDefault().unregister(this);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 0) {
//            return;
//        }
//        if (data == null) {
//            return;
//        }
//        if (requestCode == PickConfig.INSTANCE.getPICK_PHOTO_DATA()) {
//            List<String> selectPaths = (List<String>) data.getSerializableExtra(PickConfig.INSTANCE.getINTENT_IMG_LIST_SELECT());
//            // do something u want
////            Retrofit retrofit = new Retrofit.Builder().baseUrl("").build();
////            UploadPicService service = retrofit.create(UploadPicService.class);
////            RequestBody requestBody = new MultipartBody.Builder()
////            service.uploadFiles(selectPaths.get(0),)
////            //构建body
////            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
////                    .addFormDataPart("name", name)
////                    .addFormDataPart("psd", psd)
////                    .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
////                    .build();
//
//
//
//
//        }
//    }

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
                    close();
                }
            });
            llTest2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), MaterialListActivity.class);
                    startActivity(intent);
                    close();
                }
            });
            llTest4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), UploadMenuActivity.class);
                    startActivity(intent);
                    close();
                }
            });
            llTest3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), MenuDetailActivity.class);
                    MenuBean menuBean = new MenuBean();
                    menuBean.setTitle("一个西红柿的故事");
                    menuBean.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525616694411&di=6f09946cb9d619dc5c78363a30a3de2c&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D97baa57209087bf469e15fa99bab3240%2Fd6ca7bcb0a46f21f3426c46afd246b600c33aefd.jpg");
                    ArrayList<MenuStep> list  = new ArrayList<MenuStep>();
                    list.add(new MenuStep("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1525601158&di=d086112313078aa8a99ba24b7d2cd22b&src=http://pic.58pic.com/58pic/15/36/87/33M58PICxey_1024.jpg","red"));
                    list.add(new MenuStep("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525616791124&di=0d8096ce297abd8b2c89d329992f2b19&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170801%2F73d8c478d2874e2d80923c75eb755627_th.png","red"));
                    menuBean.setSteps(list);
                    intent.putExtra("MENUBEAN",menuBean);
                    startActivity(intent);
                    close();
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
