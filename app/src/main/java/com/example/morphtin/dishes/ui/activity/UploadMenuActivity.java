package com.example.morphtin.dishes.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MenuStep;
import com.swifty.dragsquareimage.DraggablePresenter;
import com.swifty.dragsquareimage.DraggablePresenterImpl;
import com.swifty.dragsquareimage.DraggableSquareView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPPToolbarActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

public class UploadMenuActivity extends BGAPPToolbarActivity implements BGASortableNinePhotoLayout.Delegate{//extends AppCompatActivity{

    private static final int PRC_PHOTO_PICKER = 1;

    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int RC_PHOTO_PREVIEW = 2;
    private static final int ADD_NEW_MENU_STEP = 7;

    private BGASortableNinePhotoLayout mPhotosSnpl;
    private List<String> images = new ArrayList<>();

   // private DraggablePresenter draggablePresent;
    private EditText menuTitle;


    private RecyclerView ItemRecyclerView;
    private ItemAdapter adapter;
    private ArrayList<MenuStep> menuList= new ArrayList<>();

        @Override
        protected void setListener() {
            // 设置拖拽排序控件的代理
            mPhotosSnpl.setDelegate(this);
        }

    // adding the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();
    }
        @Override
        protected void initView(Bundle savedInstanceState) {
            setContentView(R.layout.activity_upload_menu);
            menuTitle = (EditText) findViewById(R.id.menu_title);
            mPhotosSnpl = findViewById(R.id.snpl_moment_add_photos);
            mPhotosSnpl.setMaxItemCount(9);
            mPhotosSnpl.setEditable(true);
            mPhotosSnpl.setPlusEnable(true);
            mPhotosSnpl.setSortable(true);

            ItemRecyclerView = (RecyclerView) findViewById(R.id.menu_item_recycler_view);

            initMenuList();
            adapter = new ItemAdapter(menuList);
            ItemRecyclerView.setAdapter(adapter);

            ItemRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

    public void initMenuList(){
        //menuList.add(new MenuStep(null,null,"start from the botton"));
    }


        @Override
        protected void processLogic(Bundle savedInstanceState) {
            setTitle("上传菜谱");
        }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_menu);
//        getSupportActionBar().setTitle("上传菜谱");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        //DraggableSquareView dragSquare = (DraggableSquareView) findViewById(R.id.drag_square);
//        //draggablePresent = new DraggablePresenterImpl(this, dragSquare);
//
//        //draggablePresent.setCustomActionDialog(new MyActionDialog(this));
//        //draggablePresent.setImages(new String[]{"http://lorempixel.com/400/400?flag=0", "http://lorempixel.com/400/400?flag=1", "http://lorempixel.com/400/400?flag=2", "http://lorempixel.com/400/400?flag=3", "http://lorempixel.com/400/400?flag=4", "http://lorempixel.com/400/400?flag=5"});
//    }




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


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
//        draggablePresent.onActivityResult(requestCode, resultCode, result);
//    }

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
//        SparseArray<String> array = draggablePresent.getImageUrls();
//        if (array == null) return;


        String Title = menuTitle.getText().toString();

        menuTitle.setText(images.toString());
    }


    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
        images.remove(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mPhotosSnpl.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {
        Toast.makeText(this, "排序发生变化", Toast.LENGTH_SHORT).show();
        switchImages(fromPosition,toPosition);
    }

    public void switchImages(int from, int to){
        String stringFrom = images.get(from);
        if(from>to){
        for(int i = from;i>to;i--){
            images.set(i,images.get(i-1));
        }
            images.set(to,stringFrom);
        }else{
            for(int i = from;i<to;i++){
                images.set(i,images.get(i+1));
            }
            images.set(to,stringFrom);
        }
    }


    private void choicePhotoWrapper() {

            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(true ? takePhotoDir : null) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            if (false){//mSingleChoiceCb.isChecked()) {
                mPhotosSnpl.setData(BGAPhotoPickerActivity.getSelectedPhotos(data));
            } else {
                mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
                if(images.size() == 0){
                    images = BGAPhotoPickerActivity.getSelectedPhotos(data);
                }else {
                    images.addAll(BGAPhotoPickerActivity.getSelectedPhotos(data));
                }
            }
        } else if (requestCode == RC_PHOTO_PREVIEW) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private String ItemName;
        private String ItemImage;
        private TextView mTv;
        private ImageView imageView;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTv = itemView.findViewById(R.id.menu_item_title);
            imageView = itemView.findViewById(R.id.menu_item_photo);
        }

        public void bind(String name,String image){
            ItemName = name;
            mTv.setText(name);
            if(ItemName.equals("++++")){
                mTv.setText("添加步骤");
                imageView.setImageResource(R.drawable.addmenu);
            }else {
                imageView.setImageResource(R.drawable.vegetable);
            }
        }


        @Override
        public void onClick(View view) {
            if(ItemName.equals("++++")) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(UploadMenuActivity.this); //alert for confirm
//                builder.setMessage("++++++"); //set message
//                builder.show();
            }
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<UploadMenuActivity.ItemHolder> {

        private ArrayList<MenuStep> mData;

        public ItemAdapter(ArrayList<MenuStep> steps) {
            mData = steps;
            mData.add(new MenuStep(null,null,"++++"));
        }

        @Override
        public UploadMenuActivity.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menulist_item, parent, false);
            return new UploadMenuActivity.ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(UploadMenuActivity.ItemHolder holder, int position) {
            String name = mData.get(position).getTitle();
            String image = mData.get(position).getImageUrl();
            holder.bind(name,image);
        }


        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }


    }



    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }
        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); //get position which is swipe
            if (direction == ItemTouchHelper.LEFT) { //if swipe left
                AlertDialog.Builder builder = new AlertDialog.Builder(UploadMenuActivity.this);//MainActivity.this); //alert for confirm
                builder.setMessage("确认删除?"); //set message
                builder.setPositiveButton("是的", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemRemoved(position); //item removed from recylcerview
                        adapter.mData.remove(position);
                        return;
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() { //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemRemoved(position + 1); //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        adapter.notifyItemRangeChanged(position, adapter.getItemCount()); //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                        return;
                    }
                }).show(); //show alert dialog
            }
        }
    };
}
