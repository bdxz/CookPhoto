package com.example.morphtin.dishes.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.model.Trace;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.MenuStep;
import com.example.morphtin.dishes.ui.adapter.TraceListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DishDetailActivity extends AppCompatActivity {

    ImageView iv_maincover;
    TextView tv_title;
    ImageView iv_mainPic;
    TextView tv_author;
    TextView tv_description;
    RecyclerView rvTrace;

    private TraceListAdapter traceListadapter;
    private List<Trace> traceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        findView();
        /*
        下面两行代码测试过程中在这里调用，正式使用时应删除
         */
        MenuBean data = new MenuBean();
        showDetail(data);
    }

    private void findView() {
        rvTrace = (RecyclerView) findViewById(R.id.rvTrace);
        tv_description = (TextView)findViewById(R.id.tv_description);
        tv_author = (TextView)findViewById(R.id.tv_author);
        iv_mainPic = (ImageView)findViewById(R.id.iv_mainPic);
        tv_title = (TextView)findViewById(R.id.tv_title);
        iv_maincover = (ImageView)findViewById(R.id.iv_maincover);
    }

    public MenuBean zaojia(){
        MenuBean mb = new MenuBean();
        mb.setDescription("这是菜谱描述");
        mb.setImageTitle("jjj");
        mb.setPhoto(1);
        mb.setId(1);
        mb.setTitle("菜谱");
        ArrayList<MenuStep> list = new ArrayList<>();
        MenuStep ms1 = new MenuStep("hhh","这是步骤");
        MenuStep ms2 = new MenuStep("hhh","这是步骤");
        MenuStep ms3 = new MenuStep("hhh","这是步骤");
        MenuStep ms4 = new MenuStep("hhh","这是步骤");
        list.add(ms1);
        list.add(ms2);
        list.add(ms3);
        list.add(ms4);
        mb.setSteps(list);
        return mb;
    }

    public void showDetail(MenuBean data) {
        String stepCountDesc = "";

        data = zaojia();//zaojia()为测试时模拟的数据，当正式使用showdetail()函数时应删除该行代码

        for(int i=0;i < data.getSteps().size();i++){
            stepCountDesc = "第"+(i+1)+"步";
            Trace trace = new Trace(stepCountDesc,data.getSteps().get(i).getDescription(),data.getSteps().get(i).getImageUrl());
            traceList.add(trace);
        }
        traceListadapter = new TraceListAdapter(this, traceList);
        rvTrace.setLayoutManager(new LinearLayoutManager(this));
        rvTrace.setAdapter(traceListadapter);

        //iv_maincover.set
        tv_title.setText(data.getTitle());
        //iv_mainPic
        tv_author.setText("作者");
        tv_description.setText(data.getDescription());

    }
}
