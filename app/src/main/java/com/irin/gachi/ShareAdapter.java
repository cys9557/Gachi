package com.irin.gachi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShareAdapter extends BaseAdapter {

    ArrayList<ShareItem> members;
    LayoutInflater inflater;

    public ShareAdapter(ArrayList<ShareItem> members, LayoutInflater inflater) {
        this.members = members;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView==null){
            //res 폴더안에 layout 폴더안에 listview_food.xml 의 모양으로
            //View 객체를 생성해주는 능력을 가진 객체에게 의뢰..
            convertView= inflater.inflate(R.layout.listview_share, null);
        }
        ShareItem member= members.get(position);

        TextView title= convertView.findViewById(R.id.item_tv_title);
        TextView nickname= convertView.findViewById(R.id.item_tv_id);
        TextView time= convertView.findViewById(R.id.time);
        ImageView picture= convertView.findViewById(R.id.item_iv);
        TextView reply= convertView.findViewById(R.id.reply);
        TextView view= convertView.findViewById(R.id.num_click);



        title.setText(member.title);
        nickname.setText(member.nickname);
        time.setText(member.time);
        Glide.with(convertView).load(member.picture).into(picture);
        reply.setText(member.reply);
        view.setText(member.view+"");





        return convertView;
    }
}
