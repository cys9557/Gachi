package com.irin.gachi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends BaseAdapter {

    ArrayList<MessageItem> messageItems;
    LayoutInflater layoutInflater;

    public ChatAdapter(ArrayList<MessageItem> messageItems, LayoutInflater layoutInflater) {
        this.messageItems = messageItems;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return messageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //현재보여줄 번째(position)의 데이터로 뷰를 생성
        MessageItem item= messageItems.get(position);

        //재활용하는 view(convertView)는 사용하지 않음.
        View itemView= null;

        //메세지가 내 메세지인지...
        if(item.getName().equals(G.nickName)){
            itemView= layoutInflater.inflate(R.layout.my_msgbox, parent, false);
        }else{
            itemView= layoutInflater.inflate(R.layout.other_msgbox, parent, false);
        }

        //2. bind View
        CircleImageView iv= itemView.findViewById(R.id.iv);
        TextView tvName= itemView.findViewById(R.id.tv_name);
        TextView tvMsg= itemView.findViewById(R.id.tv_msg);
        TextView tvTime= itemView.findViewById(R.id.tv_time);


        Glide.with(itemView).load(item.getProfileUrl()).into(iv);
        tvName.setText(item.getName());
        tvMsg.setText(item.getMessage());
        tvTime.setText(item.getTime());

        return itemView;
    }
}
