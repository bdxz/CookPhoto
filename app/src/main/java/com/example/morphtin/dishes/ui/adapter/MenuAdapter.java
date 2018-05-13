package com.example.morphtin.dishes.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.Menu_in_List;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.AdapterSkeleton;
import io.rmiri.skeleton.Master.IsCanSetAdapterListener;
import io.rmiri.skeleton.SkeletonGroup;

/**
 * Created by Administrator on 2018/5/11/011.
 */

public class MenuAdapter extends AdapterSkeleton<Menu_in_List,MenuAdapter.ViewHolder> {
    public MenuAdapter(final Context context, final ArrayList<Menu_in_List> items, final RecyclerView recyclerView, final IsCanSetAdapterListener isCanSetAdapterListener) {
        this.context = context;
        this.items = items;
        this.isCanSetAdapterListener = isCanSetAdapterListener;

        measureHeightRecyclerViewAndItem(recyclerView, R.layout.item_cookbook);// Set height

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cookbook, parent, false);
        return new ViewHolder(view);
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
        final Menu_in_List cardObj = items.get(position);

        holder.titleTv.setText(cardObj.getTitle());
        holder.descriptionTv.setText(cardObj.getDescription());

        //set photo by Picasso lib
        Picasso.get().load(cardObj.getPhoto()).into(holder.photoACImgV);

    }
}
