package com.ademolaadebayo.easychops;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    private WalkThroughActivity walkThroughActivity;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context =  context;
    }
    // Array to hold Slider Content
    int [] image_Slider =  {R.drawable.jollof_rice ,R.drawable.track, R.drawable.cashorbank };
    String [] title =  {"Order","Tracking","Payment" };
    String [] message =  {"Order all you want from your favourite cafeteria and get it at stipulated time.","Real-Time tracking will keep you inform about the progress of your order.","Payment made easy , pay either by Cash or Bank." };

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ScrollView) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImage = (ImageView) view.findViewById(R.id.foodImage);
        TextView slidetitle = (TextView) view.findViewById(R.id.title);
        TextView slidemessage = (TextView) view.findViewById(R.id.message);
        TextView welcome = (TextView) view.findViewById(R.id.welcome);
        welcome.setTypeface(SplashScreenActivity.typeface);



        slideImage.setImageResource(image_Slider [position]);
        slidetitle.setText(title[position]);
        slidemessage.setText(message[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ScrollView) object);

    }
}
