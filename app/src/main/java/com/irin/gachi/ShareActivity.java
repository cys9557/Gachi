package com.irin.gachi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShareActivity extends AppCompatActivity {


    int intdata;
    String data;

    Toolbar toolbar;

    ListView listView;

    EditText addshare_title;
    EditText addshare_text;
    ImageView addshare_picture;
    ImageView dialog_close;
    Button button_ok;
    Button button_ng;

    ShareItem item;

    Uri imgUri;//갤러리앱에서 선택한 이미지의 uri

    ArrayList<ShareItem> members= new ArrayList<>();

    AlertDialog dialog;

    ShareAdapter shareAdapter;

    DatabaseReference shareRef;
    DatabaseReference shareNumber;
    DatabaseReference roomRef;

    String title;
    String nickname;
    String profileimage;
    String text;
    String picture;
    String reply;
    String time;
    int view;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        loadData();

        shareNumber= FirebaseDatabase.getInstance().getReference("shareNumber");
        shareNumber.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data= dataSnapshot.getValue().toString();
                intdata= Integer.parseInt(data);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        toolbar= findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("게시판");

        listView= findViewById(R.id.listview_share);
        shareAdapter= new ShareAdapter(members, getLayoutInflater());
        listView.setAdapter(shareAdapter);

        //'share'노드의 참조객체 얻어오기
        shareRef= FirebaseDatabase.getInstance().getReference("share");



        //'room'노드에 저장되어 있는 데이터들을 읽어오기
        //roomRef 에 데이터가 변경되는 것을 듣는 리스너 추가
        shareRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //첫번째 파라미터 : 새로 추가된 데이터노드의 정보를 가진 dataSnapshot 객체

                item= dataSnapshot.getValue(ShareItem.class);

                //리스트뷰가 보여주는 대량의 데이터인 ArrayList 에 추가
                members.add(0, item);

                //리스트뷰 화면 갱신
                shareAdapter.notifyDataSetChanged();
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
            public void onItemClick(AdapterView<?> parent, final View v, int position, long id) {

                item = members.get(position);

                ShareItem member= members.get(position);

                Intent intent= new Intent(ShareActivity.this, PostActivity.class);

                intent.putExtra("title", members.get(position).title);
                intent.putExtra("nickname", members.get(position).nickname);
                intent.putExtra("profileimage", members.get(position).profileimage);
                intent.putExtra("text", members.get(position).text);
                intent.putExtra("picture", members.get(position).picture);
                intent.putExtra("reply", members.get(position).reply);
                intent.putExtra("time", members.get(position).time);
                intent.putExtra("view", members.get(position).view);


                startActivity(intent);

                item.view++;

                shareRef.child(members.size()-position-1+"").child("view").setValue(item.view);

                shareAdapter.notifyDataSetChanged();






            }
        });

    }

    public void clickMyinform(View view) {
    }

    public void clickFab(View view) {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        final LayoutInflater inflater= getLayoutInflater();
        final View layout= inflater.inflate(R.layout.dialog_addshare, null);

        addshare_title= layout.findViewById(R.id.addshare_title);
        addshare_picture= layout.findViewById(R.id.addshare_picture);
        addshare_text= layout.findViewById(R.id.addshare_text);
        dialog_close= layout.findViewById(R.id.dialog_close);
        button_ok= layout.findViewById(R.id.ok);
        button_ng= layout.findViewById(R.id.ng);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                roomRef= shareRef.child(data);
                int index= Integer.parseInt(data)+1;
                shareNumber.setValue(index);



                //FirebaseStorage 관련 객체 얻어오기
                FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();

                //이미지파일 노드명이 중복되지 않도록..
                SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmss");
                String fileName= sdf.format( new Date())+".png";

                //'root'노드 아래에 'addImages'라는 폴더 안에 저장
                //노드참조객체 얻어오기
                final StorageReference imgRef= firebaseStorage.getReference("addImages/"+fileName);

                //이미지 업로드
                UploadTask uploadTask = imgRef.putFile(imgUri);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //이미지 업로드에 성공했으므로...
                        //업로드된 이미지의 다운로드 주소(URL)을 얻어오기
                        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //Firebase 저장소에 있는 이미지의 다운로드 주소를 문자열로..
                                Toast.makeText(ShareActivity.this, "성공", Toast.LENGTH_SHORT).show();
                                G.addPicture= uri.toString();

                                Toast.makeText(ShareActivity.this, G.addPicture, Toast.LENGTH_SHORT).show();

                                //Firebase DB 에 저장하기!!
                                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                                //'addPicture'라는 자식노드 참조
                                DatabaseReference profilesRef= firebaseDatabase.getReference("addPicture");

                                //닉네임을 키[식별자]로 하고 프로필이미지의 url 을 값으로 저장
                                profilesRef.child(G.nickName).setValue(G.addPicture);

                                //phone 에 영구적으로 닉네임과 프로필이미지경로를 저장
                                SharedPreferences pref= getSharedPreferences("addPicture", MODE_PRIVATE);
                                SharedPreferences.Editor editor= pref.edit();

                                editor.putString("addPicture", G.addPicture);

                                editor.commit();

                                title= addshare_title.getText().toString();
                                nickname= G.nickName;
                                profileimage= G.profileUrl;
                                text= addshare_text.getText().toString();
                                picture= G.addPicture;
                                reply= "0\n댓글";
//                                Calendar calendar= Calendar.getInstance();

                                // 현재시간을 msec 으로 구한다.
                                long now = System.currentTimeMillis();
                                // 현재시간을 date 변수에 저장한다.
                                Date date = new Date(now);
                                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
                                // nowDate 변수에 값을 저장한다.
                                time = sdfNow.format(date);
                                //String time= calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
                                int view= 0;

                                //메세지를 Firebase DB 에 객체 통째로 저장
                                item= new ShareItem(title, nickname, profileimage, text, picture, reply, time, view);
                                //roomRef.setValue()
                                //'room'노드에 객체 통째로 값 추가(push)
                                //shareRef.push().setValue(shareItem);
                                roomRef.push();

                                roomRef.child("title").setValue(title);
                                roomRef.child("nickname").setValue(nickname);
                                roomRef.child("profileimage").setValue(profileimage);
                                roomRef.child("text").setValue(text);
                                roomRef.child("picture").setValue(picture);
                                roomRef.child("reply").setValue(reply);
                                roomRef.child("time").setValue(time);
                                roomRef.child("view").setValue(view);
                                roomRef.child("roomnumber").setValue(data);

                                shareAdapter.notifyDataSetChanged();



                            }
                        });

                    }
                });

                dialog.dismiss();



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

    public void clickPicture(View view) {
        //갤러리 앱 실행 및 선택결과 받기
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    imgUri= data.getData();
                    Picasso.get().load(imgUri).into(addshare_picture);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void loadData(){
        SharedPreferences pref= getSharedPreferences("addPicture", MODE_PRIVATE);
        G.addPicture= pref.getString("addPicture", null);

    }


}
