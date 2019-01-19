package com.example.shiva.splashscreen;

import android.app.usage.UsageEvents;
import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView image, image2, target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageView);
        image2 = findViewById(R.id.imageView2);
        target = findViewById(R.id.txt);

        image.setOnLongClickListener(longClickListener);
        image2.setOnLongClickListener(longClickListener);
        target.setOnDragListener(dragListener);

        // moveAnimation(image, 500);
        // moveAnimation(image2, 1000);
    }

    public void moveAnimation(ImageView imageView, int time)
    {
        Animation img = new TranslateAnimation(Animation.ABSOLUTE, Animation.ABSOLUTE, Animation.ABSOLUTE, time);
        img.setDuration(3000);
        img.setFillAfter(true);
        imageView.startAnimation(img);
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener()
    {
        @Override
        public boolean onLongClick(View v)
        {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,shadowBuilder, v, 0);
            return true;
        }
    };

   View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();
            switch (dragEvent)
            {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (view.getId() == R.id.imageView)
                    {
                        view.animate()
                                .x(target.getX())
                                .y(target.getY())
                                .setDuration(700)
                                .start();
                        image.setVisibility(View.GONE);
                    }
                    if (view.getId() == R.id.imageView2)
                    {
                        recreate();
                    }
                    break;
            }
            return true;
        }
    };
}
