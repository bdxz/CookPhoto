package com.example.morphtin.dishes;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.morphtin.dishes.ui.activity.AddMenuStepActivity;
import com.example.morphtin.dishes.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Morphtin on 2018/5/21.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class StartActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void startActivityTest() {

        ViewInteraction appCompatLayoutView = onView(
                allOf(withId(R.id.center), isDisplayed()));
        appCompatLayoutView.perform(click());

        appCompatLayoutView.perform(click());

        appCompatLayoutView = onView(allOf(withId(R.id.first)));
        appCompatLayoutView.perform(click());

        appCompatLayoutView = onView(allOf(withId(R.id.home3)));
        appCompatLayoutView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.menu_title), isDisplayed()));
        appCompatEditText.perform(replaceText("一个菜谱"), closeSoftKeyboard());


        ViewInteraction appCompatButton = onView(allOf(withText("添加步骤"), isDisplayed()));
        appCompatButton.perform(click());

        appCompatEditText =  onView(allOf(withId(R.id.menuStepEditText), isDisplayed()));
        appCompatEditText.perform(replaceText("一个步骤"), closeSoftKeyboard());



        appCompatButton = onView(allOf(withText("上传"), isDisplayed()));
        appCompatButton.perform(click());

       

//        ViewInteraction appCompatImage = onView(allOf(withId(R.id.menuStepImageView)));
//        appCompatImage.perform(click());




//        //根据id和显示的文本内容找到控件,并判断是否可见
//        ViewInteraction appCompatButton2 = onView(
//                allOf(withId(R.id.getPassword), withText("获取验证码"), isDisplayed()));
//        //执行该控件的点击操作
//        appCompatButton2.perform(click());
//        //根据id找到控件,并判断是否可见
//        ViewInteraction appCompatEditText2 = onView(
//                allOf(withId(R.id.password), isDisplayed()));
//        //执行替换文本操作,说白了就是输入文本,输入完毕之后关闭输入法键盘
//        appCompatEditText2.perform(replaceText("2454"), closeSoftKeyboard());
//        //根据id和显示的文本内容找到控件,并判断是否可见
//        ViewInteraction appCompatButton3 = onView(
//                allOf(withId(R.id.login), withText("登录"), isDisplayed()));
//        //执行该控件的点击操作
//        appCompatButton3.perform(click());
    }
}
