package com.example.morphtin.dishes.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.morphtin.dishes.R;


/**
 * Created by elevation on 18-4-4.
 */

public class BottomBar extends LinearLayout {
    private Context mContext;

    private FrameLayout mFirst_bottom, mSecond_bottom, mThird_bottom, mFouth_bottom, mCenter_bottom;
    private OnBottonbarClick mOnBottonbarClick;

    public BottomBar(Context context) {
        super(context);
        init(context);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_bottom, this, true);
        //获取控件id
        initId();
        onBottomBarClick();
    }


    private void initId() {
        mFirst_bottom = findViewById(R.id.first);
        mSecond_bottom = findViewById(R.id.second);
        mThird_bottom = findViewById(R.id.third);
        mFouth_bottom = findViewById(R.id.fouth);
        mCenter_bottom = findViewById(R.id.center);
    }

    /**
     * 底部按钮点击监听器
     */
    private void onBottomBarClick() {

        mFirst_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottonbarClick != null) {
                    mOnBottonbarClick.onFirstClick();
                }
            }
        });
        mSecond_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottonbarClick != null) {
                    mOnBottonbarClick.onSecondClick();
                }
            }
        });
        mThird_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottonbarClick != null) {
                    mOnBottonbarClick.onThirdClick();
                }
            }
        });
        mFouth_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottonbarClick != null) {
                    mOnBottonbarClick.onFouthClick();
                }
            }
        });
        mCenter_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottonbarClick != null) {
                    mOnBottonbarClick.onCenterClick();
                }
            }
        });

    }

    public void setOnBottombarOnclick(OnBottonbarClick onBottonbarClick) {

        mOnBottonbarClick = onBottonbarClick;
    }

    public interface OnBottonbarClick {
        void onFirstClick();

        void onSecondClick();

        void onThirdClick();

        void onFouthClick();

        void onCenterClick();
    }
}
