package com.irin.gachi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {

    ArrayList<FoodItem> members;
    LayoutInflater inflater;

    public FoodAdapter(ArrayList<FoodItem> members, LayoutInflater inflater) {
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
            convertView= inflater.inflate(R.layout.listview_food, null);
        }

        ImageView iv= convertView.findViewById(R.id.item_iv);
        TextView tvName= convertView.findViewById(R.id.item_tv_name);
        TextView tvMsg= convertView.findViewById(R.id.item_tv_msg);

        FoodItem member= members.get(position);

        iv.setImageResource(member.imgID);
        tvName.setText(member.name);
        tvMsg.setText(member.msg);



        return convertView;
    }
}
