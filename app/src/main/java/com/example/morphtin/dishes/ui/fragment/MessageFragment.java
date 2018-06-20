package com.example.morphtin.dishes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.MessageContract;
import com.example.morphtin.dishes.api.presenter.MessagePresenter;
import com.example.morphtin.dishes.ui.activity.MainActivity;
import com.example.morphtin.dishes.ui.activity.MessageActivity;
import com.example.morphtin.dishes.ui.base.BaseFragment;
import com.example.morphtin.dishes.bean.Message;
import com.example.morphtin.dishes.ui.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elevation on 18-4-4.
 */

public class MessageFragment extends BaseFragment implements MessageContract.View{
    private static final String TAG = "MessageFragment";
    private ListView listView;
    private List<Message> message_List;
    private MessageAdapter adapter;
    private MessageContract.Presenter presenter;

    public static MessageFragment newInstance() {
        Bundle bundle = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.loadMessage();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        message_List = new ArrayList<>();

        //MessageActivity activity = (MessageActivity) getActivity();
        adapter = new MessageAdapter (getActivity(),R.layout.fragment_message_item,message_List);
        listView=(ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        presenter = new MessagePresenter(this);
        return view;
    }

    public void setListViewHeight(ListView listview) {
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for(int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData(boolean isSavedNull) {

    }
    @Override
    public void showMessage(List<Message> data) {
        message_List.clear();
        message_List.addAll(data);
        Log.d(TAG, "showMessage: "+data.size());
        setListViewHeight(listView);
        adapter.notifyDataSetChanged();
    }
}
