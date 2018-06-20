package com.example.morphtin.dishes.ui.fragment;

import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.morphtin.dishes.Constant.P;
import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MomentBean;
import com.example.morphtin.dishes.bean.SimpleUserBean;
import com.example.morphtin.dishes.ui.activity.ImagePreviewActivity;
import com.example.morphtin.dishes.ui.adapter.HomeIndexAdapter;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.example.morphtin.dishes.ui.base.BaseHelper;
import com.example.morphtin.dishes.ui.view.OnItemPictureClickListener;
import com.example.morphtin.dishes.util.Utils;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by elevation on 18-4-4.
 */

public class DiscoveryFragment extends BaseFragment {

    private static final String TAG="DiscoveryFragment";
    public static final String content ="1.腐竹提前泡软，切小段。\n" +
            "2.开水里焯烫2分钟。\n" +
            "3.捞出沥去水分。\n" +
            "4.黄瓜用刀拍碎。\n" +
            "5.斜着切小块。\n" +
            "6.将腐竹和黄瓜放入盆中。\n" +
            "7.加入蒜末。\n" +
            "8.加入鸡精、蚝油、盐、香油、醋，搅拌均匀即可。\n" +
            "9.装盘食用。";

    private View fragmentView;
    private RecyclerView recyclerView;
    private HomeIndexAdapter homeIndexAdapter;
    private List<MomentBean> momentBeanList;
    private Bundle   mReenterState;
    private int itemPosition;
    public static DiscoveryFragment newInstance() {
        Bundle bundle = new Bundle();

        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        fragmentView = view;
        initView();
        initData();
        doLogic();
        return view;
    }

    //给RecyclerView 设置Oritaion :vertical
    private void doLogic() {
        if(this.getActivity()==null){
            Log.d(TAG, "doLogic: activity==null");
        }

        if(recyclerView==null){
            Log.d(TAG, "doLogic: recyclerView==null");
        }
        BaseHelper.setLinearLayoutManagerVertical(this.getActivity(),recyclerView,homeIndexAdapter);
    }

    //初始化
    private void initView() {
        Log.d(TAG, "initView:1 ");
        if(fragmentView!=null) {
            Log.d(TAG, "initView: 2");
            recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recyclerView_moment);
            if(recyclerView==null){
                Log.d(TAG, "initView: "+"recyclerView等于null");
            }
        }
    }

    /**
     * 初始化momentBeanList
     */
    private void initData() {
        List<String> imageList = new ArrayList<>();
        imageList.add("http://site.meishij.net/r/184/180/4420184/s4420184_150183411333837.jpg");
        imageList.add("http://s1.ig.meishij.net/p/20180619/bcafa8dc0bfad9e54ff7331082206ccc.jpg");
        //imageList.add("http://img1.imgtn.bdimg.com/it/u=3356331771,2093090619&fm=214&gp=0.jpg");
        imageList.add("http://s1.ig.meishij.net/p/20180619/be953ce2e21bd01f5db88b30a8c1d146.jpg");
        imageList.add("http://site.meishij.net/r/54/216/9241554/s9241554_150208757018508.jpg");
        imageList.add("http://s1.ig.meishij.net/p/20180619/93c15e0f8ac0842f3376adff6d45a323.jpg");
        imageList.add("http://s1.ig.meishij.net/p/20180619/1f7700ce59476c98dca1cd1c489cdd77.jpg");
        imageList.add("http://s1.ig.meishij.net/p/20180619/d7b7694211aaae2c9ec377d6f5e11121.jpg");
        imageList.add("http://s1.ig.meishij.net/p/20180619/d7b7694211aaae2c9ec377d6f5e11121.jpg");
        imageList.add("http://s1.st.meishij.net/r/32/219/3992282/s3992282_152471223734657.jpg");

        momentBeanList = new ArrayList<>();
        for(int i=0;i<9;i++){
            MomentBean momentBean = new MomentBean();
            //设置用户
            SimpleUserBean sub = new SimpleUserBean();
            sub.setImageUrl("http://cuimg.zuyushop.com/cuxiaoPic/201511/2015110010091817554.jpg");
            sub.setUsername("王鹏");
            momentBean.setContent(content);
            momentBean.setImageUrls(imageList.subList(0,i));
            momentBean.setMomentID(i);
            momentBean.setSimpleUserBean(sub);

            momentBeanList.add(momentBean);
        }

        homeIndexAdapter = new HomeIndexAdapter(this.getActivity(), momentBeanList, new OnItemPictureClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemPictureClick(int item, int position, String url, List<String> urlList, ImageView imageView) {
                itemPosition = item;
                Intent intent = new Intent(DiscoveryFragment.this.getActivity(), ImagePreviewActivity.class);
                intent.putStringArrayListExtra("imageList", (ArrayList<String>) urlList);
                intent.putExtra(P.START_ITEM_POSITION, itemPosition);
                intent.putExtra(P.START_IAMGE_POSITION, position);
                ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(DiscoveryFragment.this.getActivity(), imageView, imageView.getTransitionName());
                startActivity(intent, compat.toBundle());
            }
        });
    }

//    private void initShareElement() {
//        setExitSharedElementCallback(mCallback);
//    }

//    public void onActivityReenter(int requestCode, Intent data) {
//        super.onActivityReenter(requestCode, data);
//        mReenterState = new Bundle(data.getExtras());
//        int startingPosition = mReenterState.getInt(P.CURRENT_ITEM_POSITION);
//        if (startingPosition != itemPosition) {//如果不是同一个item就滚动到指定的item
//            recyclerView.scrollToPosition(itemPosition);
//        }
//        postponeEnterTransition();
//        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
//                recyclerView.requestLayout();
//                startPostponedEnterTransition();
//                return true;
//            }
//        });
//    }


//    private final SharedElementCallback mCallback = new SharedElementCallback() {
//
//        @Override
//        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
//            if (mReenterState != null) {
//                //从别的界面返回当前界面
//                int startingPosition = mReenterState.getInt(P.START_IAMGE_POSITION);
//                int currentPosition = mReenterState.getInt(P.CURRENT_IAMGE_POSITION);
//                if (startingPosition != currentPosition) {//如果不是之前的那张图片就切换到指定的图片
//                    String newTransitionName = Utils.getNameByPosition(itemPosition,currentPosition);
//                    View newSharedElement = recyclerView.findViewWithTag(newTransitionName);
//                    if (newSharedElement != null) {
//                        names.clear();
//                        names.add(newTransitionName);
//                        sharedElements.clear();
//                        sharedElements.put(newTransitionName, newSharedElement);
//                    }
//                }
//                mReenterState = null;
//            }
//        }
//    };


    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }
}
