package com.example.morphtin.dishes.ui.adapter;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.api.contract.ChooseContract;
import com.example.morphtin.dishes.bean.Message;
import java.util.List;

/**
 * Created by Administrator on 2018/6/5 0005.
 */

public class MessageAdapter extends ArrayAdapter<Message> {
    private int resoureId;
    private Message message;
    public MessageAdapter(Context context, int ResourceId, List<Message> objects) {
        super(context, ResourceId, objects);
        resoureId = ResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resoureId, parent, false);
        ImageView messageImage = (ImageView)view.findViewById(R.id.messageImage);
        TextView titleText = (TextView)view.findViewById(R.id.textView1);
        TextView despText = (TextView)view.findViewById(R.id.textView2);
        TextView timeText = (TextView)view.findViewById(R.id.textView3);
        messageImage.setImageResource(message.getImageID());
        titleText.setText(message.getTitle());
        despText.setText(message.getDscp());
        timeText.setText(message.getTime());
        return view;
    }
}


