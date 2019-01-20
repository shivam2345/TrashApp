package com.example.shiva.splashscreen;

import android.animation.ObjectAnimator;
import android.app.usage.UsageEvents;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity {
    ImageView image, image2, image3, target, target2;
    TextView points, debugchannel;
    static int score = 0;
    private int _xDelta;
    private int _yDelta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        target2 = findViewById(R.id.trash);
        debugchannel = findViewById(R.id.debug);
        target = findViewById(R.id.actualtrash);
        points = findViewById(R.id.pts);

        points.setText("" + score);
        image.setOnTouchListener(new ChoiceTouchListener());
        image2.setOnTouchListener(new ChoiceTouchListener());
        image3.setOnTouchListener(new ChoiceTouchListener());
        //image2.setOnLongClickListener(longClickListener);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        image.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(360, 160);
        image2.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(360, 160);
        image3.setLayoutParams(layoutParams);
        moveAnimation(image, 500f);
        moveAnimation(image2, 1000f);
        moveAnimation(image3, 1500f);
        // moveAnimation(image, 500);
        // moveAnimation(image2, 1000);
    }

    ObjectAnimator animation;
    public void moveAnimation(ImageView imageView, float moveValue)
    {
        animation = ObjectAnimator.ofFloat(imageView, "translationY", moveValue);
        animation.setDuration(5000);
        animation.start();
    }
    private final class ChoiceTouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    animation.cancel();
                    break;
                case MotionEvent.ACTION_UP:
                    int targetRight = target.getRight();
                    int targetLeft = target.getLeft();
                    int targetTop = target.getTop();
                    int targetBottom = target.getBottom();

                    int targetRight2 = target2.getRight();
                    int targetLeft2 = target2.getLeft();
                    int targetTop2 = target2.getTop();
                    int targetBottom2 = target2.getBottom();

                    if (view.getId() == R.id.imageView) {
                        int imgRight = image.getRight() ;
                        int imgLeft = image.getLeft();
                        int imgTop = image.getTop();
                        int imgBottom = image.getBottom();

                        debugchannel.setText("target right: " + targetRight + "; target left: " + targetLeft + "; img right: " + imgRight + "; img left: " + imgLeft
                                + "target top: " + targetTop + "; target bottom: " + targetBottom + "; img top: " + imgTop + "; img bottom: " + imgBottom);

                        if (targetRight > imgRight && targetLeft < imgLeft && targetTop > imgTop && targetBottom > imgBottom && image.getTag().equals("trash")) {
                            image.setVisibility(View.GONE);
                            score++;
                            points.setText("" + score);
                            //debugchannel.setText("yeaaaa");

                        }
                        else if(targetRight2 > imgRight && targetLeft2 < imgLeft && targetTop2 > imgTop && targetBottom2 > imgBottom && image.getTag().equals("trash"))
                        {
                            hitCheck();
                        }
                    }
                    else if(view.getId()==R.id.imageView2)
                    {
                        int imgRight = image2.getRight() ;
                        int imgLeft = image2.getLeft();
                        int imgTop = image2.getTop();
                        int imgBottom = image2.getBottom();

                        debugchannel.setText("target right: " + targetRight + "; target left: " + targetLeft + "; img right: " + imgRight + "; img left: " + imgLeft
                                + "target top: " + targetTop + "; target bottom: " + targetBottom + "; img top: " + imgTop + "; img bottom: " + imgBottom);

                        if (targetRight > imgRight && targetLeft < imgLeft && targetTop > imgTop && targetBottom > imgBottom && image.getTag().equals("trash")) {

                            hitCheck();
                            //debugchannel.setText("yeaaaa");

                        }
                        else if(targetRight2 > imgRight && targetLeft2 < imgLeft && targetTop2 > imgTop && targetBottom2 > imgBottom && image.getTag().equals("trash"))
                        {
                            image2.setVisibility(View.GONE);
                            score++;
                            points.setText("" + score);
                        }
                    }

                    else if(view.getId()==R.id.imageView3)
                    {
                        int imgRight = image3.getRight() ;
                        int imgLeft = image3.getLeft();
                        int imgTop = image3.getTop();
                        int imgBottom = image3.getBottom();

                        //debugchannel.setText("target right: " + targetRight + "; target left: " + targetLeft + "; img right: " + imgRight + "; img left: " + imgLeft
                          //      + "target top: " + targetTop + "; target bottom: " + targetBottom + "; img top: " + imgTop + "; img bottom: " + imgBottom);

                        if (targetRight > imgRight && targetLeft < imgLeft && targetTop > imgTop && targetBottom > imgBottom && image.getTag().equals("trash")) {


                            //debugchannel.setText("yeaaaa");
                            image3.setVisibility(View.GONE);
                            score++;
                            points.setText("" + score);

                        }
                        else if(targetRight2 > imgRight && targetLeft2 < imgLeft && targetTop2 > imgTop && targetBottom2 > imgBottom && image.getTag().equals("trash"))
                        {
                            hitCheck();
                        }
                    }
                    /*int target2Right = target2.getRight();
                    int target2Left = target2.getLeft();
                    int target2Top = target2.getTop();
                    int target2Bottom = target2.getBottom();

                    if (target2Right > imgRight && target2Left < imgLeft && target2Top < imgTop && target2Bottom > imgBottom && img.getTag().equals("recycle")) {
                        img.setVisibility(View.GONE);
                    }*/

                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                            .getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;
            }
            return true;
        }
    }
    public void hitCheck() {
        //show result
        Intent intent = new Intent(getApplicationContext(), result.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }

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
                        //Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                        image.setVisibility(View.GONE);
                        score++;
                        points.setText("Score: "+ score);
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