package com.irin.gachi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {

    ImageView iv;

    Timer timer= new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv= findViewById(R.id.iv);

        Animation ani= AnimationUtils.loadAnimation(this, R.anim.appear_logo);
        iv.startAnimation(ani);

        //스케줄 등록
        timer.schedule(task,2000); //2초후에 task 객체가 start()
    }

    //멤버변수 위치
    //Thread 를 상속받아 만들어진 TimerTask 라는 클래스를 객체로 생성
    TimerTask task= new TimerTask() {
        @Override
        public void run() {
            //MainActivity 를 실행!!
            Intent intent= new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);

            //IntroActivity 를 메모리에서 완전 제거!!
            finish();

        }
    };
}
