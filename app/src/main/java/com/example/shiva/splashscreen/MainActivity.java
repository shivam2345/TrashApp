package com.example.shiva.splashscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView image, image2;
    items bananapeel = new items(true,10,R.drawable.banana, 0, 0);
    items applecore = new items(true,20,R.drawable.apple, 0, 0);
    items plasticbottle = new items(true,30,R.drawable.banana, 0, 0);
    items papercup = new items(true,40,R.drawable.banana,0, 0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        image2 = findViewById(R.id.imageView2);

        image.setImageResource(bananapeel.imageID);
        image2.setImageResource(bananapeel.imageID);
        moveAnimation(image, 500);
        moveAnimation(image2, 1000);
    }

    public void moveAnimation(ImageView imageView, int time)
    {
        Animation img = new TranslateAnimation(Animation.ABSOLUTE, Animation.ABSOLUTE, Animation.ABSOLUTE, time);
        img.setDuration(3000);
        img.setFillAfter(true);
        imageView.startAnimation(img);
    }

}
