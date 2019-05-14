package com.irin.gachi;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv_title;
    CircleImageView iv_profile;
    TextView tv_id;
    TextView tv_time;
    TextView num_click;
    ImageView iv_add;
    TextView tv_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        toolbar = findViewById(R.id.toolbar);
        tv_title = findViewById(R.id.item_tv_title);
        iv_profile= findViewById(R.id.item_iv_profile);
        tv_id= findViewById(R.id.item_tv_id);
        tv_time= findViewById(R.id.tv_time);
        num_click= findViewById(R.id.num_click);
        iv_add= findViewById(R.id.iv_add);
        tv_text= findViewById(R.id.item_tv_text);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title();

        Intent intent = getIntent();



        String title = intent.getExtras().getString("title");
        String nickname = intent.getExtras().getString("nickname");
        String profileimage= intent.getExtras().getString("profileimage");
        String text = intent.getExtras().getString("text");
        String picture = intent.getExtras().getString("picture");
        String reply = intent.getExtras().getString("reply");
        String time = intent.getExtras().getString("time", "");
        int view = intent.getIntExtra("view", 0);

        tv_title.setText(title);
        tv_id.setText(nickname);
        Glide.with(this).load(profileimage).into(iv_profile);
        tv_time.setText(time);
        num_click.setText(String.valueOf(view));
        tv_text.setText(text);
        Glide.with(this).load(picture).into(iv_add);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clickMyinform(View view) {

    }

    void title() {
        getSupportActionBar().setTitle("가치");

    }
}
