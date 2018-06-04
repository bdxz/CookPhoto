package com.example.morphtin.dishes.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MaterialBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by elevation on 18-5-21.
 */

public class TagsAdapter extends RecyclerView.Adapter<TagHolder>{
    private static final String TAG = "TagsAdapter";
    private final Context mContext;
    private HashMap<String,List<MaterialBean>> mTagsMap = new HashMap<>();
    private List<TagHolder> holderList = new ArrayList<>();

    public TagsAdapter(Context mContext,HashMap<String,List<MaterialBean>> tagsMap) {
        this.mContext = mContext;
        this.mTagsMap = tagsMap;
    }

    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.title_tags, parent, false);
        TagHolder th = new TagHolder(v,mContext);
        holderList.add(th);
        return th;
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {
        Iterator<Map.Entry<String, List<MaterialBean>>> iterator = mTagsMap.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if(i == position){
                Map.Entry<String, List<MaterialBean>> entry = iterator.next();
                String catelog = entry.getKey();
                Log.d(TAG, "onBindViewHolder: "+catelog);
                List<MaterialBean> list = entry.getValue();
                holder.bind(catelog,list);
                break;
            }else{
                i++;
                iterator.next();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mTagsMap == null ? 0 : mTagsMap.size();
    }

    public List<TagHolder> getHolderList(){
        return holderList;
    }
}
