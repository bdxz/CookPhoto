package com.example.morphtin.dishes.ui.fragment;

import android.animation.AnimatorSet;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.ui.activity.MainActivity;
import com.example.morphtin.dishes.ui.base.BaseFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class LoginFragment extends BaseFragment {

    EditText email, pass, email2, pass2, confirmPass;
    RelativeLayout relativeLayout, relativeLayout2;
    LinearLayout mainLinear,img;
    TextView signUp,login,forgetPass;
    ImageView logo,back;
    LinearLayout.LayoutParams params, params2;
    FrameLayout.LayoutParams params3;
    FrameLayout mainFrame;
    ObjectAnimator animator2, animator1;

    public static LoginFragment newInstance() {
        Bundle bundle = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params3 = new FrameLayout.LayoutParams(inDp(50), inDp(50));

        signUp = (TextView) view.findViewById(R.id.signUp);
        login = (TextView) view.findViewById(R.id.login);
        email = (EditText) view.findViewById(R.id.email);
        pass = (EditText) view.findViewById(R.id.pass);
        img = (LinearLayout) view.findViewById(R.id.img);
        email2 = (EditText) view.findViewById(R.id.email2);

        forgetPass = (TextView) view.findViewById(R.id.forget);
        pass2 = (EditText) view.findViewById(R.id.pass2);
        mainFrame = (FrameLayout) view.findViewById(R.id.loginFrame);
        confirmPass = (EditText) view.findViewById(R.id.pass3);
        back = (ImageView) view.findViewById(R.id.backImg);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative);
        relativeLayout2 = (RelativeLayout) view.findViewById(R.id.relative2);
        mainLinear = (LinearLayout) view.findViewById(R.id.mainLinear);

        logo = new ImageView(getActivity());
        logo.setImageResource(R.drawable.logo);
        logo.setLayoutParams(params3);

        relativeLayout.post(new Runnable() {
            @Override
            public void run() {

                logo.setX((relativeLayout2.getRight() / 2));
                logo.setY(inDp(50));
                mainFrame.addView(logo);
            }
        });

        params.weight = (float) 0.75;
        params2.weight = (float) 4.25;

        mainLinear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                mainLinear.getWindowVisibleDisplayFrame(r);
                int screenHeight = mainFrame.getRootView().getHeight();


                int keypadHeight = screenHeight - r.bottom;


                if (keypadHeight > screenHeight * 0.15) {
                    // keyboard is opened
                    if (params.weight == 4.25) {

                        animator1 = ObjectAnimator.ofFloat(back, "scaleX", (float) 1.95);
                        animator2 = ObjectAnimator.ofFloat(back, "scaleY", (float) 1.95);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animator1, animator2);
                        set.setDuration(1000);
                        set.start();

                    } else {

                        animator1 = ObjectAnimator.ofFloat(back, "scaleX", (float) 1.75);
                        animator2 = ObjectAnimator.ofFloat(back, "scaleY", (float) 1.75);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animator1, animator2);
                        set.setDuration(500);
                        set.start();
                    }
                } else {
                    // keyboard is closed
                    animator1 = ObjectAnimator.ofFloat(back, "scaleX", 3);
                    animator2 = ObjectAnimator.ofFloat(back, "scaleY", 3);
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(animator1, animator2);
                    set.setDuration(500);
                    set.start();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                if (params.weight == 4.25) {
                    if(email2.getText().toString().split("@") == null){
                        flag=false;
                        Snackbar.make(relativeLayout2, "邮箱错误", Snackbar.LENGTH_SHORT).show();
                        return ;
                    }else if(pass2.getText().toString().length()<6 || !pass2.getText().toString().equals(confirmPass.getText().toString())){
                        flag=false;
                        Snackbar.make(relativeLayout2, "密码错误", Snackbar.LENGTH_SHORT).show();
                        return ;
                    }

                    if (flag) {
                        Snackbar.make(relativeLayout, "Sign Up Complete", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    return;
                }


                email2.setVisibility(View.VISIBLE);
                pass2.setVisibility(View.VISIBLE);
                confirmPass.setVisibility(View.VISIBLE);

                final ChangeBounds bounds = new ChangeBounds();
                bounds.setDuration(1500);
                bounds.addListener(new Transition.TransitionListener() {

                    @Override
                    public void onTransitionStart(Transition transition) {


                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(signUp, "translationX", mainLinear.getWidth() / 2 - relativeLayout2.getWidth() / 2 - signUp.getWidth() / 2);
                        ObjectAnimator animator2 = ObjectAnimator.ofFloat(img, "translationX", -relativeLayout2.getX());
                        ObjectAnimator animator3 = ObjectAnimator.ofFloat(signUp, "rotation", 0);

                        ObjectAnimator animator4 = ObjectAnimator.ofFloat(email, "alpha", 1, 0);
                        ObjectAnimator animator5 = ObjectAnimator.ofFloat(pass, "alpha", 1, 0);
                        ObjectAnimator animator6 = ObjectAnimator.ofFloat(forgetPass, "alpha", 1, 0);

                        ObjectAnimator animator7 = ObjectAnimator.ofFloat(login, "rotation", 90);
                        ObjectAnimator animator8 = ObjectAnimator.ofFloat(login, "y", relativeLayout2.getHeight() / 2);
                        ObjectAnimator animator9 = ObjectAnimator.ofFloat(email2, "alpha", 0, 1);

                        ObjectAnimator animator10 = ObjectAnimator.ofFloat(confirmPass, "alpha", 0, 1);
                        ObjectAnimator animator11 = ObjectAnimator.ofFloat(pass2, "alpha", 0, 1);
                        ObjectAnimator animator12 = ObjectAnimator.ofFloat(signUp, "y", login.getY());

                        ObjectAnimator animator13 = ObjectAnimator.ofFloat(back, "translationX", img.getX());
                        ObjectAnimator animator14 = ObjectAnimator.ofFloat(signUp, "scaleX", 2);
                        ObjectAnimator animator15 = ObjectAnimator.ofFloat(signUp, "scaleY", 2);

                        ObjectAnimator animator16 = ObjectAnimator.ofFloat(login, "scaleX", 1);
                        ObjectAnimator animator17 = ObjectAnimator.ofFloat(login, "scaleY", 1);
                        ObjectAnimator animator18 = ObjectAnimator.ofFloat(logo, "x", relativeLayout2.getRight() / 2 - relativeLayout.getRight());

                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animator1, animator2, animator3, animator4, animator5, animator6, animator7,
                                animator8, animator9, animator10, animator11, animator12, animator13, animator14, animator15, animator16, animator17, animator18);
                        set.setDuration(1500).start();



                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {


                        email.setVisibility(View.INVISIBLE);
                        pass.setVisibility(View.INVISIBLE);
                        forgetPass.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {


                    }
                });

                TransitionManager.beginDelayedTransition(mainLinear, bounds);

                params.weight = (float) 4.25;
                params2.weight = (float) 0.75;


                relativeLayout.setLayoutParams(params);
                relativeLayout2.setLayoutParams(params2);



            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                if (params2.weight == 4.25) {
                    if(email.getText().toString().split("@") == null){
                        flag = false;
                        Snackbar.make(relativeLayout2, "邮箱错误", Snackbar.LENGTH_SHORT).show();
                        return ;
                    }else if(pass.getText().toString().length()<6){
                        flag = false;
                        Snackbar.make(relativeLayout2, "密码错误", Snackbar.LENGTH_SHORT).show();
                        return ;
                    }
                    if (flag) {

                        SharedPreferences sp = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name","xiang");
                        editor.commit();

                        Snackbar.make(relativeLayout2, "Login Complete", Snackbar.LENGTH_SHORT).show();
                        SupportFragment supportFragment = MineFragment.newInstance();
                        showHideFragment(supportFragment,getTopFragment());
                        MainFragment.getInstance().initData(true);
                        return;
                    }
                        return ;
                }

                email.setVisibility(View.VISIBLE);
                pass.setVisibility(View.VISIBLE);
                forgetPass.setVisibility(View.VISIBLE);


                final ChangeBounds bounds = new ChangeBounds();
                bounds.setDuration(1500);
                bounds.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {


                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(login, "translationX", mainLinear.getWidth() / 2 - relativeLayout.getWidth() / 2 - login.getWidth() / 2);
                        ObjectAnimator animator2 = ObjectAnimator.ofFloat(img, "translationX", (relativeLayout.getX()));
                        ObjectAnimator animator3 = ObjectAnimator.ofFloat(login, "rotation", 0);

                        ObjectAnimator animator4 = ObjectAnimator.ofFloat(email, "alpha", 0, 1);
                        ObjectAnimator animator5 = ObjectAnimator.ofFloat(pass, "alpha", 0, 1);
                        ObjectAnimator animator6 = ObjectAnimator.ofFloat(forgetPass, "alpha", 0, 1);

                        ObjectAnimator animator7 = ObjectAnimator.ofFloat(signUp, "rotation", 90);
                        ObjectAnimator animator8 = ObjectAnimator.ofFloat(signUp, "y", relativeLayout.getHeight() / 2);
                        ObjectAnimator animator9 = ObjectAnimator.ofFloat(email2, "alpha", 1, 0);

                        ObjectAnimator animator10 = ObjectAnimator.ofFloat(confirmPass, "alpha", 1, 0);
                        ObjectAnimator animator11 = ObjectAnimator.ofFloat(pass2, "alpha", 1, 0);
                        ObjectAnimator animator12 = ObjectAnimator.ofFloat(login, "y", signUp.getY());

                        ObjectAnimator animator13 = ObjectAnimator.ofFloat(back, "translationX", -img.getX());
                        ObjectAnimator animator14 = ObjectAnimator.ofFloat(login, "scaleX", 2);
                        ObjectAnimator animator15 = ObjectAnimator.ofFloat(login, "scaleY", 2);

                        ObjectAnimator animator16 = ObjectAnimator.ofFloat(signUp, "scaleX", 1);
                        ObjectAnimator animator17 = ObjectAnimator.ofFloat(signUp, "scaleY", 1);
                        ObjectAnimator animator18 = ObjectAnimator.ofFloat(logo, "x", logo.getX()+relativeLayout2.getWidth());


                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animator1, animator2, animator3, animator4, animator5, animator6, animator7,
                                animator8, animator9, animator10, animator11, animator12, animator13, animator14, animator15, animator16, animator17,animator18);
                        set.setDuration(1500).start();

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {

                        email2.setVisibility(View.INVISIBLE);
                        pass2.setVisibility(View.INVISIBLE);
                        confirmPass.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });

                TransitionManager.beginDelayedTransition(mainLinear, bounds);

                params.weight = (float) 0.75;
                params2.weight = (float) 4.25;

                relativeLayout.setLayoutParams(params);
                relativeLayout2.setLayoutParams(params2);
                Log.d("登录", ""+email.getText());





            }
        });

        return view;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }

    private int inDp(int dp) {

        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
