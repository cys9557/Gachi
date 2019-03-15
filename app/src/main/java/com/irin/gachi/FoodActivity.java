package com.irin.gachi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {


    Toolbar toolbar;

    ListView listView;
    FoodAdapter foodAdapter;

    ArrayList<FoodItem> members= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        toolbar= findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("음식");

        listView= findViewById(R.id.listview_food);
        foodAdapter= new FoodAdapter(members, getLayoutInflater());
        listView.setAdapter(foodAdapter);

        member();

        foodAdapter.notifyDataSetChanged();


    }

    public void clickMyinform(View view) {
    }

    void member(){
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
        members.add(new FoodItem(R.drawable.myinform, "라면", "존맛탱"));
    }
}
