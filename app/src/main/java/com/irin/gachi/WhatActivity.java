package com.irin.gachi;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class WhatActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what);

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

    public void clickMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void clickFood(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);
    }

    public void clickFacility(View view) {
        Intent intent = new Intent(this, FacilityActivity.class);
        startActivity(intent);
    }

    public void clickShare(View view) {
        Intent intent = new Intent(this, ShareActivity.class);
        startActivity(intent);
    }

    void init() {
        G.ch_waterpark = false;
        G.ch_skipark = false;
        G.ch_themepark = false;
    }

    void title() {
        if(G.ch_waterpark) getSupportActionBar().setTitle("가치-워터파크-무엇을");
        if(G.ch_skipark) getSupportActionBar().setTitle("가치-스키장-무엇을");
        if(G.ch_themepark) getSupportActionBar().setTitle("가치-놀이동산-무엇을");
    }
}

