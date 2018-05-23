package com.example.morphtin.dishes.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.MaterialBean;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morphtin on 2018/5/22.
 */

public class TagHolder extends RecyclerView.ViewHolder{
    private final Context mContext;
    private TextView title;
    private TagAdapter tagAdapter;
    private  FlowTagLayout flowTagLayout;
    private List<MaterialBean> materialBeanList = new ArrayList<>();
    private List<MaterialBean> selectedMaterialBeanList = new ArrayList<>();


        public TagHolder(View itemView,Context context) {
            super(itemView);
            this.mContext = context;
            tagAdapter = new TagAdapter(mContext);
            title = itemView.findViewById(R.id.catelog);
            flowTagLayout = itemView.findViewById(R.id.flow_layout);
            flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
            flowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
                @Override
                public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                    if (selectedList != null && selectedList.size() > 0) {
                        selectedMaterialBeanList.clear();
                        for (int i : selectedList) {
                           selectedMaterialBeanList.add(materialBeanList.get(i));
                        }
//                Snackbar.make(parent, "蔬菜:" + sb.toString(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            });

        }

        public void bind(String catelog,List<MaterialBean> list){
            title.setText(catelog);
            flowTagLayout.setAdapter(tagAdapter);
            materialBeanList.addAll(list);
            tagAdapter.onlyAddAll(list);
        }

        public List<MaterialBean> getSelectedMaterialBeanList(){
            return selectedMaterialBeanList;
        }


}
