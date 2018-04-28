package com.example.morphtin.dishes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.presenter.IMaterialPresenter;
import com.example.morphtin.dishes.api.presenter.impl.MaterialPresenterImpl;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.example.morphtin.dishes.ui.adapter.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by elevation on 18-4-25.
 */

public class ChoosePhotoActivity extends SupportActivity{
    private PhotoAdapter adapter;

    @BindView(R.id.photo_list)
    RecyclerView photoList;
    @BindView(R.id.upload_photo)
    ActionProcessButton mProcessButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);

        ButterKnife.bind(this);

        final ArrayList<String> photoPaths = getIntent().getStringArrayListExtra("photoPaths");
        adapter = new PhotoAdapter(this,photoPaths);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        photoList.setLayoutManager(layoutManager);
        photoList.setAdapter(adapter);

        adapter.updateData(photoPaths);

        mProcessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoosePhotoActivity.this,MaterialListActivity.class);
                startActivity(intent);
            }
        });
    }
}
