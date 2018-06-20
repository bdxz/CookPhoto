package com.example.morphtin.dishes.ui.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.MenuContract;
import com.example.morphtin.dishes.api.presenter.MenuPresenter;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.MenuStep;
import com.example.morphtin.dishes.ui.base.BaseActivity;


import java.util.ArrayList;
import java.util.List;


import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class UploadMenuActivity extends BaseActivity implements MenuContract.View{
    private static final String TAG = "UploadMenuActivity";
    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int ADD_NEW_MENU_STEP = 7;

    private CircleImageView mCircleImageView;
    private String imagePath;

    private EditText menuTitle;


    private RecyclerView ItemRecyclerView;
    private ItemAdapter adapter;
    private ArrayList<MenuStep> menuList= new ArrayList<>();

    private MenuContract.Presenter presenter;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_menu);
        getSupportActionBar().setTitle("上传菜谱");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCircleImageView = (CircleImageView)findViewById(R.id.menu_image);
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
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
        menuTitle = (EditText) findViewById(R.id.menu_title);
        ItemRecyclerView = (RecyclerView) findViewById(R.id.menu_item_recycler_view);

        adapter = new ItemAdapter(menuList);
        ItemRecyclerView.setAdapter(adapter);

        ItemRecyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(ItemRecyclerView); //set swipe to recylcerview

        presenter = new MenuPresenter(this);

    }

    @Override
    protected void initEvents() {

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



    public void uploadMenu() {
        String Title = menuTitle.getText().toString();

        MenuBean menuBean = new MenuBean();
        menuBean.setTitle(Title);
        menuBean.setImageUrl(imagePath);
        menuBean.setSteps(menuList);

        presenter.addMenu(menuBean);
        Log.d(TAG, "uploadMenu: ");
    }

    public void addAMenuStep(View v){
        Intent intent = new Intent(getApplicationContext(),AddMenuStepActivity.class);
        startActivityForResult(intent, ADD_NEW_MENU_STEP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
                imagePath = BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
                mCircleImageView.setImageURI(Uri.parse(BGAPhotoPickerActivity.getSelectedPhotos(data).get(0)));
        }else if(resultCode == RESULT_OK && requestCode == ADD_NEW_MENU_STEP){
            MenuStep menuStep = data.getParcelableExtra("MENUSTEP");
            menuList.add(menuStep);
        }
    }

    @Override
    public void showDetail(MenuBean data) {
        //不用实现
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
            imageView.setImageURI(Uri.parse(image));

        }


        @Override
        public void onClick(View view) {
            if(ItemName.equals("++++")) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(UploadMenuActivity.this); //alert for confirm
//                builder.setMessage("++++++"); //set message
//                builder.show();
                Intent intent = new Intent(getApplicationContext(),AddMenuStepActivity.class);
                startActivityForResult(intent, ADD_NEW_MENU_STEP);
            }
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<UploadMenuActivity.ItemHolder> {

        private ArrayList<MenuStep> mData;

        public ItemAdapter(ArrayList<MenuStep> steps) {
            mData = steps;
        }

        @Override
        public UploadMenuActivity.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menulist_item, parent, false);
            return new UploadMenuActivity.ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(UploadMenuActivity.ItemHolder holder, int position) {
            String name = mData.get(position).getDescription();
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
