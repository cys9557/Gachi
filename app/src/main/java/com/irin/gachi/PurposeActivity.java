package com.irin.gachi;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class PurposeActivity extends AppCompatActivity {

    Toolbar toolbar;

    ImageView iv_together;
    ImageView iv_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purpose);

        toolbar= findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("목적");
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

    public void clickTogether(View view) {

        G.ch_together= true;

        Intent intent= new Intent(this, WhereActivity.class);
        startActivity(intent);
    }

    public void clickValue(View view) {

        G.ch_value= true;

        Intent intent= new Intent(this, WhereActivity.class);
        startActivity(intent);
    }

}
