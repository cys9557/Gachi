package com.irin.gachi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WaitingActivity extends AppCompatActivity {

    Toolbar toolbar;

    ListView listView;

    EditText et_age_min;
    EditText et_age_max;
    Spinner spinner_location;
    EditText dialog_title;
    ImageView dialog_close;
    Button button_ok;
    Button button_ng;

    AlertDialog dialog;


    ArrayList<WaitingItem> members= new ArrayList<>();

    WaitingAdapter waitingAdapter;

    ArrayAdapter adapter;

    DatabaseReference roomRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        toolbar= findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title();

        getSupportActionBar().setTitle("");

        listView= findViewById(R.id.listview_waiting);
        waitingAdapter= new WaitingAdapter(members, getLayoutInflater());
        listView.setAdapter(waitingAdapter);

        //'room'노드의 참조객체 얻어오기
        roomRef= FirebaseDatabase.getInstance().getReference("room");

        //'room'노드에 저장되어 있는 데이터들을 읽어오기
        //roomRef 에 데이터가 변경되는 것을 듣는 리스너 추가
        roomRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //첫번째 파라미터 : 새로 추가된 데이터노드의 정보를 가진 dataSnapshot 객체

                WaitingItem waitingItem= dataSnapshot.getValue(WaitingItem.class);

                //리스트뷰가 보여주는 대량의 데이터인 ArrayList 에 추가
                members.add(0, waitingItem);

                //리스트뷰 화면 갱신
                waitingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                WaitingItem waitingItem = members.get(position);

                Bundle extras = new Bundle();
                extras.putString("makeRoomTime", waitingItem.makeRoomTime);
                extras.putString("location", waitingItem.location);
                G.makeRoom= false;

                Intent intent= new Intent(WaitingActivity.this, ChatActivity.class);

                intent.putExtras(extras);
                startActivity(intent);

//                G.roomNumber++;
//
//                SharedPreferences pref= getSharedPreferences("account", MODE_PRIVATE);
//                SharedPreferences.Editor editor= pref.edit();
//
//                editor.putInt("roomNumber", G.roomNumber);
//
//                editor.commit();



            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        init();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                init();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clickMyinform(View view) {
    }



    void init() {
        G.ch_waterpark = false;
        G.ch_skipark = false;
        G.ch_themepark = false;
    }

    void title(){
        if(G.ch_waterpark) getSupportActionBar().setTitle("같이하기-워터파크");
        if(G.ch_skipark) getSupportActionBar().setTitle("같이하기-스키장");
        if(G.ch_themepark) getSupportActionBar().setTitle("같이하기-놀이동산");
    }

    public void clickMakeroom(View view) {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        final LayoutInflater inflater= getLayoutInflater();
        final View layout= inflater.inflate(R.layout.dialog_makeroom, null);

        et_age_min= layout.findViewById(R.id.age_min);
        et_age_max= layout.findViewById(R.id.age_max);
        spinner_location= layout.findViewById(R.id.spinner_location);
        dialog_title= layout.findViewById(R.id.makeroom_title);
        dialog_close= layout.findViewById(R.id.dialog_close);
        button_ok= layout.findViewById(R.id.ok);
        button_ng= layout.findViewById(R.id.ng);


        if(G.ch_skipark){
            adapter= ArrayAdapter.createFromResource(this, R.array.location_ski, R.layout.spinner_item);
        }else if(G.ch_waterpark){
            adapter= ArrayAdapter.createFromResource(this, R.array.location_waterpark, R.layout.spinner_item);
        }else if(G.ch_themepark){
            adapter= ArrayAdapter.createFromResource(this, R.array.location_themepark, R.layout.spinner_item);
        }


        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner_location.setAdapter(adapter);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Resources res= getResources();
                if(G.ch_skipark){
                    String[] arr= res.getStringArray(R.array.location_ski);
                    G.location= arr[position];
                }else if(G.ch_waterpark){
                    String[] arr= res.getStringArray(R.array.location_waterpark);
                    G.location= arr[position];
                }else if(G.ch_themepark){
                    String[] arr= res.getStringArray(R.array.location_themepark);
                    G.location= arr[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.room_title= dialog_title.getText().toString();
                G.room_age_min= et_age_min.getText().toString();
                G.room_age_max= et_age_max.getText().toString();

                long time = System.currentTimeMillis();
                SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String makeRoomTime = dayTime.format(new Date(time));

                G.makeRoom= true;

                dialog.dismiss();

                String title= G.room_title;
                String location= G.location;
                String age_min= G.room_age_min;
                String age_max= G.room_age_max;
                G.makeRoomTime= makeRoomTime;


                //메세지를 Firebase DB 에 객체 통째로 저장
                WaitingItem waitingItem= new WaitingItem(title, location, age_min, age_max, makeRoomTime);

                //'room'노드에 객체 통째로 값 추가(push)
                roomRef.push().setValue(waitingItem);

                //ChatActivity 이동
                Intent intent= new Intent(WaitingActivity.this, ChatActivity.class);
                startActivity(intent);



            }
        });

        button_ng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        builder.setView(layout);

        dialog = builder.create();

        dialog.show();

    }

    public void clickFilter(View view) {


    }
}
