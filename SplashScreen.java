package com.example.shahin.test;

/**
 * Created by Shahin on 11/4/15 AD.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        MediaPlayer mpSplash = MediaPlayer.create(this, R.raw.delicate_bell_ljh);
        mpSplash.start();


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);


                }
            }
        };
        timerThread.start();
        TextView txtView = (TextView) findViewById(R.id.SplashText);
        txtView.startAnimation(AnimationUtils.loadAnimation(SplashScreen.this, R.anim.pull_in_right));
        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        imgView.startAnimation(AnimationUtils.loadAnimation(SplashScreen.this, R.anim.pull_in_left));

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}