package com.example.morphtin.dishes.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.morphtin.dishes.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * created by wangpeng
 */
public class ImagePreviewAdapter extends PagerAdapter {
    private Context context;
    private List<String> imageList;
    private int itemPosition; //RecycleView中的Position
    private PhotoView photoView;
    public ImagePreviewAdapter(Context context, List<String> imageList, int itemPosition) {
        this.context = context;
        this.imageList = imageList;
        this.itemPosition = itemPosition;
    }

    @Override
    public int getCount() {
        return imageList==null?0:imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final PhotoView image = new PhotoView(context);
        image.setEnabled(true);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image.setMaximumScale(2.0F);
        image.setMinimumScale(0.8F);

        Picasso.get().load(imageList.get(position)).into(image);


       image.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               image.setEnabled(false);
               ((Activity)context).onBackPressed();
           }
       });
        container.addView(image);
        return image;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        photoView = (PhotoView) object;
        photoView.setTag(Utils.getNameByPosition(itemPosition,position));
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public PhotoView getPhotoView() {
        return photoView;
    }
}
