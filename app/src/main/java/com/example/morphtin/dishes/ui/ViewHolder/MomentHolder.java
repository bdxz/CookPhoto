package com.example.morphtin.dishes.ui.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MomentBean;
import com.example.morphtin.dishes.bean.SimpleUserBean;
import com.example.morphtin.dishes.ui.base.BaseHolder;
import com.example.morphtin.dishes.ui.view.NineGridTestLayout;
import com.example.morphtin.dishes.ui.view.OnItemPictureClickListener;
import com.squareup.picasso.Picasso;

public class MomentHolder extends BaseHolder<MomentBean>{
    private ImageView iv_user_icon;
    private TextView tv_username;
    private TextView tv_moment_content;
    private TextView tv_close_open;
    private NineGridTestLayout nineGridTestLayout;
    private OnItemPictureClickListener listener;
    public MomentHolder(View itemView,OnItemPictureClickListener listener){
        super(itemView);
        this.listener = listener ;
    }

    @Override
    public void initView(View view) {
        iv_user_icon = (ImageView)view.findViewById(R.id.iv_user_icon);
        tv_username = (TextView)view.findViewById(R.id.tv_user_name);
        tv_moment_content = (TextView)view.findViewById(R.id.tv_moment_content);
        tv_close_open = (TextView)view.findViewById(R.id.tv_close_open);
        nineGridTestLayout = (NineGridTestLayout)view.findViewById(R.id.nineTestlayout);
    }

    @Override
    public void bindViewHolder(MomentBean momentBean, int position) {
        SimpleUserBean sub  = momentBean.getSimpleUserBean();
        Picasso.get().load(R.mipmap.ic_launcher_round).into(iv_user_icon);
        tv_username.setText(sub.getUsername());
        tv_moment_content.setText(momentBean.getContent());
        tv_close_open.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String text = tv_close_open.getText().toString();
                if(text.equals("展开")){

                    tv_close_open.setText("合起");
                    tv_moment_content.setMaxLines(Integer.MAX_VALUE);
                    tv_moment_content.requestLayout();
                    return;
                }

                if(text.equals("合起")){
                    tv_moment_content.setMaxLines(2);
                    tv_close_open.setText("展开");
                    tv_moment_content.requestLayout();
                    return;
                }

            }
        });
        nineGridTestLayout.setListener(listener);
        nineGridTestLayout.setItemPosition(position);
        nineGridTestLayout.setIsShowAll(false); //当传入的图片数超过9张时，是否全部显示
        nineGridTestLayout.setSpacing(5); //动态设置图片之间的间隔
        nineGridTestLayout.setUrlList(momentBean.getImageUrls());

    }
}
