package com.example.morphtin.dishes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.morphtin.dishes.R;
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

public class MessageFragment extends BaseFragment {
    private ListView listView;
    private List<Message> message_List;

    public static MessageFragment newInstance() {
        Bundle bundle = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        List<Message> list = new ArrayList<Message>();
        Message message3 = new Message(R.drawable.coook, "厨你拍新功能上线了！", "制作你的菜谱，分享到平台，共同体验做菜的乐趣", "2018.6.4");
        Message message4 = new Message(R.drawable.coook, "加入厨你拍大家庭！", "厨你拍团队招募，只要你有热情，爱做菜，快来加入我们吧", "2018.6.4");
        list.add(message3);
        list.add(message4);
        Message message = setMessageFromDatabase(list);
        //在这里测试调用接口
        message_List = message.getMessage();
        //MessageActivity activity = (MessageActivity) getActivity();
        MessageAdapter array = new MessageAdapter (getActivity(),R.layout.fragment_message_item,message_List);
        listView=(ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(array);
        setListViewHeight(listView);
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

    public void addMessage(List<Message> messageList)
    {

    }

    public  Message setMessageFromDatabase(List<Message> list) {
        Message message1 = new Message();
        message1.setMessage(list);
        return message1;
    }

}
