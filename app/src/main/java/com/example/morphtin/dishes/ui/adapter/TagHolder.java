package com.example.morphtin.dishes.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.hhl.library.FlowTagLayout;

import java.util.List;

/**
 * Created by Morphtin on 2018/5/22.
 */

public class TagHolder extends RecyclerView.ViewHolder{
    private final Context mContext;
    private TextView title;
    private TagAdapter tagAdapter;
    private  FlowTagLayout flowTagLayout;


        public TagHolder(View itemView,Context context) {
            super(itemView);
            this.mContext = context;
            tagAdapter = new TagAdapter(mContext);
            title = itemView.findViewById(R.id.catelog);
            flowTagLayout = itemView.findViewById(R.id.flow_layout);
            flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        }

        public void bind(String catelog,List<MaterialBean> list){
            title.setText(catelog);
            flowTagLayout.setAdapter(tagAdapter);

            tagAdapter.onlyAddAll(list);
        }


}
