package com.irin.gachi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WaitingAdapter extends BaseAdapter {

    ArrayList<WaitingItem> members;
    LayoutInflater inflater;

    public WaitingAdapter(ArrayList<WaitingItem> members, LayoutInflater inflater) {
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
            convertView= inflater.inflate(R.layout.listview_waiting, null);
        }

        TextView title= convertView.findViewById(R.id.title);
        TextView location= convertView.findViewById(R.id.location);
        TextView age= convertView.findViewById(R.id.age);

        WaitingItem member= members.get(position);

        title.setText("제목 : "+member.title);
        location.setText("위치 : "+member.location);
        age.setText("나이 : "+member.age_min+ "세 ~ "+member.age_max+"세");



        return convertView;
    }
}
