package com.example.morphtin.dishes.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.morphtin.dishes.R;
import com.werb.pickphotoview.util.PickConfig;
import com.werb.pickphotoview.util.PickUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-4-25.
 */

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<String> imagePaths;
    private Context context;

    public PhotoAdapter(Context c, ArrayList<String> imagePaths) {
        this.context = c;
        this.imagePaths = imagePaths;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GridImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(imagePaths != null) {
            String path = imagePaths.get(position);
            GridImageViewHolder gridImageViewHolder = (GridImageViewHolder) holder;
            gridImageViewHolder.bindItem(path);
        }
    }

    @Override
    public int getItemCount() {
        if(imagePaths != null) {
            return imagePaths.size();
        }else {
            return 0;
        }
    }

    public void updateData(ArrayList<String> paths) {
        imagePaths = paths;
        notifyDataSetChanged();
    }

    // ViewHolder
    private class GridImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView gridImage;
        private int scaleSize;

        GridImageViewHolder(View itemView) {
            super(itemView);
            gridImage = itemView.findViewById(R.id.image);
            int screenWidth = PickUtils.getInstance(context.getApplicationContext()).getWidthPixels();
            int space = PickUtils.getInstance(context.getApplicationContext()).dp2px(PickConfig.INSTANCE.getITEM_SPACE());
            scaleSize = (screenWidth - (4 + 1) * space) / 4;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) gridImage.getLayoutParams();
            params.width = scaleSize;
            params.height = scaleSize;
        }

        void bindItem(final String path) {
            gridImage.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }
}
