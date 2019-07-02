package com.ademolaadebayo.easychops;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import me.anwarshahriar.calligrapher.Calligrapher;

public class WalkThroughActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout linearLayout; TextView mDots [];
     public static Button startBtn;
    private SliderAdapter sliderAdapter;

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

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Fonts/Poppins-Regular.ttf", true);
        setContentView(R.layout.activity_walk_through);
        viewPager = findViewById(R.id.viewpager);
        linearLayout = findViewById(R.id.pagernavlayout);
        startBtn = findViewById(R.id.startBtn);
        startBtn.setVisibility(View.GONE);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDotIndicator(0);
         viewPager.setOnPageChangeListener(viewListner);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalkThroughActivity.this,SignIn.class);
                startActivity(intent);
            }
        });

    }

    public void addDotIndicator(int p){
        mDots = new TextView[3];
        linearLayout.removeAllViews();
        for (int i = 0; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.appName));
           linearLayout.addView(mDots[i]);

        }

        mDots[p].setTextColor(getResources().getColor(R.color.pinklike));

        if (p != 2){
           startBtn.setVisibility(View.GONE);
        }else {
            startBtn.setVisibility(View.VISIBLE);

        }

    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotIndicator(position);
            Log.i("CHECK",""+position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
