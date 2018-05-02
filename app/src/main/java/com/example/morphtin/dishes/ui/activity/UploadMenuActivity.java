package com.example.morphtin.dishes.ui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.morphtin.dishes.R;
import com.swifty.dragsquareimage.DraggablePresenter;
import com.swifty.dragsquareimage.DraggablePresenterImpl;
import com.swifty.dragsquareimage.DraggableSquareView;

public class UploadMenuActivity extends AppCompatActivity {

    private DraggablePresenter draggablePresent;
    private EditText menuTitle;
    private EditText menuDetails;
    // adding the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_menu);
        getSupportActionBar().setTitle("上传菜谱");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DraggableSquareView dragSquare = (DraggableSquareView) findViewById(R.id.drag_square);
        draggablePresent = new DraggablePresenterImpl(this, dragSquare);
        menuTitle = (EditText) findViewById(R.id.menu_title);
        menuDetails = (EditText)findViewById(R.id.menu_details);
        draggablePresent.setCustomActionDialog(new MyActionDialog(this));
        //draggablePresent.setImages(new String[]{"http://lorempixel.com/400/400?flag=0", "http://lorempixel.com/400/400?flag=1", "http://lorempixel.com/400/400?flag=2", "http://lorempixel.com/400/400?flag=3", "http://lorempixel.com/400/400?flag=4", "http://lorempixel.com/400/400?flag=5"});
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            case R.id.check_ok:
                uploadMenu();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        draggablePresent.onActivityResult(requestCode, resultCode, result);
    }

    /**
     * 根据Uri获取图片文件的绝对路径
     */
    public String getAbsolutePath(final Uri uri) {
        if (null == uri) {
            return null;
        }

        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public void uploadMenu() {
        SparseArray<String> array = draggablePresent.getImageUrls();
        if (array == null) return;


        String Title = menuTitle.getText().toString();
        String details = menuDetails.getText().toString();
    }
}
