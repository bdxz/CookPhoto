package com.example.morphtin.dishes.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MenuStep;
import com.example.morphtin.dishes.ui.base.BaseActivity;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;

public class AddMenuStepActivity extends BaseActivity {
    private static final int RC_CHOOSE_PHOTO = 1;

    private MenuStep menuStep = new MenuStep("","");
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_step);
        getSupportActionBar().setTitle("编辑菜谱");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.menuStepImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(getApplicationContext())
                        .cameraFileDir(null) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                        .maxChooseCount(1) // 图片选择张数的最大值
                        .selectedPhotos(null) // 当前已选中的图片路径集合
                        .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                        .build();
                startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {

                menuStep.setImageUrl(BGAPhotoPickerActivity.getSelectedPhotos(data).get(0));
                imageView.setImageURI(Uri.parse(BGAPhotoPickerActivity.getSelectedPhotos(data).get(0)));

        }
    }

    public void sendMenuStep(View view){

        EditText editText = (EditText) findViewById(R.id.menuStepEditText);
        String description = editText.getText().toString();
        menuStep.setDescription(description);


        new AlertDialog.Builder(this)
                .setTitle("消息")
                .setMessage("添加成功")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        Intent data = new Intent();
                        data.putExtra("MENUSTEP", menuStep); //company is the new company, SOME_KEY is the key for the data
                        setResult(RESULT_OK, data);
                        finish();
                    }

                }).show();


    }
}
