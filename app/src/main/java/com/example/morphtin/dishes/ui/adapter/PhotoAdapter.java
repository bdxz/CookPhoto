package com.example.morphtin.dishes.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by elevation on 18-4-25.
 */

public class PhotoAdapter extends BaseAdapter{
    private List<String> photoPaths;
    private Context context;

    public PhotoAdapter(Context context,List<String> photoPaths) {
        this.context = context;
        this.photoPaths = photoPaths;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
