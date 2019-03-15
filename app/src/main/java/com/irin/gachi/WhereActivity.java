package com.irin.gachi;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class WhereActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        init();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                init();
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clickMyinform(View view) {
    }

    public void clickWaterpark(View view) {
        G.ch_waterpark= true;
        wheretogo();

    }

    public void clickSkipark(View view) {
        G.ch_skipark= true;
        wheretogo();
    }

    public void clickThemepark(View view) {
        G.ch_themepark= true;
        wheretogo();
    }

    void wheretogo(){
        if(G.ch_together){
            Intent intent= new Intent(this, WaitingActivity.class);
            startActivity(intent);
        }

        if(G.ch_value){
            Intent intent= new Intent(this, WhatActivity.class);
            startActivity(intent);
        }
    }

    void init(){
        G.ch_together= false;
        G.ch_value= false;
    }

    void title(){
        if(G.ch_together)getSupportActionBar().setTitle("같이->위치");
        if(G.ch_value)getSupportActionBar().setTitle("가치->위치");
    }

}


