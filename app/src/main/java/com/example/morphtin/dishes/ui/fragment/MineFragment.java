package com.example.morphtin.dishes.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.common.service.DownloadService;
import com.example.morphtin.dishes.bean.User;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.example.morphtin.dishes.ui.view.EditMineDialog;

import butterknife.BindView;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by elevation on 18-4-4.
 */

public class MineFragment extends BaseFragment {

    private CircleImageView mCircleImageView;
    private User user;
    private TextView textViewName;
    private TextView textViewAge;
    private TextView textViewDescription;
    private LinearLayout profileLayout;
    private Button logout_btn;

    public static MineFragment newInstance() {
        Bundle bundle = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mCircleImageView = (CircleImageView) view.findViewById(R.id.profile_image);
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(getActivity())
                        .cameraFileDir(null) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                        .maxChooseCount(1) // 图片选择张数的最大值
                        .selectedPhotos(null) // 当前已选中的图片路径集合
                        .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                        .build();
                startActivityForResult(photoPickerIntent, 233);
                    }
        });
        textViewName = view.findViewById(R.id.profile_name);
        textViewAge = view.findViewById(R.id.profile_age);
        textViewDescription=view.findViewById(R.id.profile_details);
        profileLayout = view.findViewById(R.id.profile_layout);
        TextView test_btn = view.findViewById(R.id.test_btn);
        logout_btn = view.findViewById(R.id.logout);

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent downloadIntent = new Intent(getContext(), DownloadService.class);
                downloadIntent.putExtra("menu_id",1);
                getContext().startService(downloadIntent);
            }
        });

         profileLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditMineDialog dialog = new EditMineDialog(getActivity(),new EditMineDialog.DialogListener() {
                        @Override
                        public void refreshActivity(Object object) {
                            User middle=(User)object;
                            middle.setImage(user.getImage());
                            User.getUser(getActivity()).updateUser(user.getName(),(User) object);
                            user=middle;
                            setProfile(user.getName());//点击确定之后的
                        }});
                    dialog.show();


                }
            });
            setProfile("昵称");//最开始初始化的时候需要,我认为应该先

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("name");
                editor.commit();
                Toast.makeText(getContext(),"退出登录成功",Toast.LENGTH_SHORT).show();
                SupportFragment supportFragment = LoginFragment.newInstance();
                showHideFragment(supportFragment,getTopFragment());
                MainFragment.getInstance().initData(true);
            }
        });
        return view;
    }

    public void setProfile(String name){
        user = User.getUser(getActivity()).getUserInf(name);
        //if (name == "昵称" && user.getDescription()=="a") user.getDescription("我已经不是")

        textViewName.setText(user.getName());
        textViewAge.setText(user.getAge()+"岁");
        textViewDescription.setText(user.getDescription());
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //mRootView = inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }
}
