package com.example.morphtin.dishes.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Description
 * Created by Administrator
 * Time 2018/1/3  21:46
 */

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {

    private BaseAdapter<T> adapter;

    public BaseHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    /**
     * 初始化View
     *
     * @param view
     */
    public abstract void initView(View view);

    /**
     *
     *  给ViewHold绑定数据
     *
     * @param t     绑定到改ViewHolder的数据
     * @param position  该ViewHolder在Adapter中的位置
     */
    public abstract void bindViewHolder(T t, int position);
    public void bindViewHolderPayLoads(T t, int position, List<Object> payloads){}


    void bindViewHolder(T t, int position, List<Object> payloads){
        if(payloads==null){
            bindViewHolder(t,position);
        }else {
            bindViewHolderPayLoads(t,position,payloads);
        }
    }

    public BaseAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseAdapter<T> adapter) {
        this.adapter = adapter;
    }
}
