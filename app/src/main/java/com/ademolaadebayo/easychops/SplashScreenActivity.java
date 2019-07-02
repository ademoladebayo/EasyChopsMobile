package com.ademolaadebayo.easychops;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    TextView appName; Animation frombottom , zoom;
    public  static Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else
        {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        setContentView(R.layout.splash_screen);
        appName = findViewById(R.id.appName);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
         typeface = Typeface.createFromAsset(getAssets(),"Fonts/Andhyta.ttf");
        appName.setTypeface(typeface);
        appName.setAnimation(frombottom);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, WalkThroughActivity.class);
                    startActivity(intent);
                    finish();
            }
        },7000);


    }




}
