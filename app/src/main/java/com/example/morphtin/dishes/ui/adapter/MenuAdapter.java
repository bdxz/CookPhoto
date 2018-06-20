package com.example.morphtin.dishes.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.common.service.DownloadService;
import com.example.morphtin.dishes.bean.MenuBean;
import com.example.morphtin.dishes.bean.Menu_in_List;
import com.example.morphtin.dishes.ui.activity.MenuDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.rmiri.skeleton.Master.AdapterSkeleton;
import io.rmiri.skeleton.Master.IsCanSetAdapterListener;
import io.rmiri.skeleton.SkeletonGroup;

/**
 * Created by Administrator on 2018/5/11/011.
 */

public class MenuAdapter extends AdapterSkeleton<MenuBean,MenuAdapter.ViewHolder> {
    public MenuAdapter(final Context context, final List<MenuBean> items, final RecyclerView recyclerView, final IsCanSetAdapterListener isCanSetAdapterListener) {
        this.context = context;
        this.items = items;
        this.isCanSetAdapterListener = isCanSetAdapterListener;

        measureHeightRecyclerViewAndItem(recyclerView, R.layout.item_cookbook);// Set height

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cookbook, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.photoACImgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, MenuDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MenuDetailActivity.EXTRA_MENU_ID,items.get(position).getMenu_id());
                context.startActivity(intent);
            }
        });

        holder.addToParkingImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent downloadIntent = new Intent(context, DownloadService.class);
                Log.d("菜谱", "onClick: "+items.get(position).getMenu_id()+items.get(position).getImageUrl());
                downloadIntent.putExtra("menu_id",items.get(position).getMenu_id());
                downloadIntent.putExtra("url",items.get(position).getImageUrl());
                downloadIntent.putExtra("name",items.get(position).getTitle());
                context.startService(downloadIntent);

                Toast.makeText(getContext(),"开始下载图片",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private SkeletonGroup skeletonGroup;
        private AppCompatImageView photoACImgV;
        private TextView titleTv;
        private TextView descriptionTv;
        private AppCompatImageButton addToParkingImgBtn;


        ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            skeletonGroup = (SkeletonGroup) itemView.findViewById(R.id.skeletonGroup);
            photoACImgV = (AppCompatImageView) itemView.findViewById(R.id.photoACImgV);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            descriptionTv = (TextView) itemView.findViewById(R.id.descriptionTv);
            addToParkingImgBtn = (AppCompatImageButton) itemView.findViewById(R.id.addToParkingImgBtn);

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.cardView.setPreventCornerOverlap(false);

//        holder.skeletonGroup.setPosition(position);//just for debug log

        if (skeletonConfig.isSkeletonIsOn()) {
            return;
        } else {
            holder.skeletonGroup.setShowSkeleton(false);
            holder.skeletonGroup.finishAnimation();
        }

        //set data in view
        final MenuBean cardObj = items.get(position);

        holder.titleTv.setText(cardObj.getTitle());
        holder.descriptionTv.setText(cardObj.getTitle());

        //set photo by Picasso lib
        Picasso.get().load(cardObj.getImageUrl()).into(holder.photoACImgV);

    }
}
