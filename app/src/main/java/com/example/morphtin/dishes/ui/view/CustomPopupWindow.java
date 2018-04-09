package com.example.morphtin.dishes.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.morphtin.dishes.R;

import butterknife.BindView;
import butterknife.BindViews;

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
    @BindViews({R.id.test1, R.id.test2, R.id.test3, R.id.test4})
    LinearLayout llTest1, llTest2, llTest3, llTest4;

    public CustomPopupWindow(Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.popup_menu, null);

        setContentView(view);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setFocusable(false);

        // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        setClippingEnabled(false);
        setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setOutsideTouchable(true);

        top = dip2px(context, 310);
        bottom = dip2px(context, 210);
        animatorProperty = new float[]{bottom, 60, -30, -20 - 10, 0};
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

    @Override
    public void dismiss() {
        super.dismiss();
        closeAnimation();
    }

    private void closeAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 135f, 0f);
        objectAnimator.setDuration(200);
        objectAnimator.start();

//        translation(llTest1, 300, top);
//        translation(llTest2, 200, top);
//        translation(llTest3, 200, top);
//        translation(llTest4, 300, top);
//
//        rlClick.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                _close();
//            }
//        }, 300);
    }

    private void translation(View view, int duration, float[] distance) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
        anim.setDuration(duration);
        anim.start();
    }
}
