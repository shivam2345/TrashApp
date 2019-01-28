package com.example.shiva.splashscreen;

import android.animation.ObjectAnimator;
import android.app.usage.UsageEvents;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
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
    private int count;

    static int relativeposimage1top=0;
    static int relativeposimage1bot=0;
    static int relativeposimage2top=0;
    static int relativeposimage2bot=0;
    static int relativeposimage3top=0;
    static int relativeposimage3bot=0;
    int dimensionswidth=700;     ///arbitrary value because view.getWidth() sCREwed Up mY pHONe Real bAD gUYS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        debugchannel = findViewById(R.id.debug);

        target2 = findViewById(R.id.trash);
        target = findViewById(R.id.actualtrash);
        //RelativeLayout.LayoutParams layoutParamstrash = new RelativeLayout.LayoutParams(150, 150);
        //target2.setLayoutParams(layoutParamstrash);
        //RelativeLayout.LayoutParams layoutParamsactualtrash = new RelativeLayout.LayoutParams(150, 150);
        //target.setLayoutParams(layoutParamsactualtrash);

        points = findViewById(R.id.pts);
        score = 0;
        count = 0;
        points.setText("" + score);

        image.setOnTouchListener(new ChoiceTouchListener());
        image2.setOnTouchListener(new ChoiceTouchListener());
        image3.setOnTouchListener(new ChoiceTouchListener());
        //image2.setOnLongClickListener(longClickListener);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);

        layoutParams.setMargins((int)(Math.random()*dimensionswidth),150,300,500);
        //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        image.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(150, 150);
        layoutParams1.setMargins((int)(Math.random()*dimensionswidth),150,300,500);
        image2.setLayoutParams(layoutParams1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(150, 150);
        layoutParams2.setMargins((int)(Math.random()*dimensionswidth),150,300,500);
        //layoutParams2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        image3.setLayoutParams(layoutParams2);
        moveAnimation(image, 500f);
        moveAnimation(image2, 500f);
        moveAnimation(image3, 500f);
        // moveAnimation(image, 500);
        // moveAnimation(image2, 1000);
    }

    ObjectAnimator animation;
    public void moveAnimation(ImageView imageView, float moveValue)
    {
        animation = ObjectAnimator.ofFloat(imageView, "translationY", moveValue);
        animation.setDuration(2000);
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
                    if(animation.isRunning())
                    {
                        relativeposimage2bot = 0-(int)image2.getY()-150;
                        relativeposimage2top = 0-(int)image2.getY()-300;

                        relativeposimage1bot = 0-(int)image.getY()-150;
                        relativeposimage1top = 0-(int)image.getY()-300;

                        relativeposimage3bot = 0-(int)image3.getY()-150;
                        relativeposimage3top = 0-(int)image3.getY()-300;
                        animation.cancel();
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    int targetRight = target.getRight();
                    int targetLeft = target.getLeft();
                    int targetTop = target.getTop();
                    int targetBottom = target.getBottom()+150;

                    int targetRight2 = target2.getRight();
                    int targetLeft2 = target2.getLeft();
                    int targetTop2 = target2.getTop();
                    int targetBottom2 = target2.getBottom();

                    if (view.getId() == R.id.imageView) {
                        int imgRight = image.getRight()-70;
                        int imgLeft = image.getLeft()+70;
                        int imgTop = image.getTop()-relativeposimage1top+70;
                        int imgBottom = image.getBottom()-relativeposimage1bot-70;

                /*debugchannel.setText("target right: " + targetRight + "; target left: " + targetLeft + "; img right: " + imgRight + "; img left: " + imgLeft
                                + "target top: " + targetTop + "; target bottom: " + targetBottom + "; img top: " + imgTop + "; img bottom: " + imgBottom);
*/
                        if (targetRight > imgRight && targetLeft < imgLeft && targetTop < imgTop
                                && targetBottom > imgBottom && image.getTag().equals("trash")) {
                            image.setVisibility(View.GONE);
                            score++;
                            points.setText("" + score);
                            count++;
                        }
                        else if(targetRight2 > imgRight && targetLeft2 < imgLeft && targetTop2 < imgTop
                                && targetBottom2 > imgBottom && image.getTag().equals("trash"))
                        {
                            hitCheck();
                        }
                    }
                    else if(view.getId()==R.id.imageView2)
                    {
                        int imgRight = image2.getRight()-70;
                        int imgLeft = image2.getLeft()+70;
                        int imgTop = image2.getTop()-relativeposimage2top+70;
                        int imgBottom = image2.getBottom()-relativeposimage2bot-70;

                       debugchannel.setText("target right: " + targetRight + "; target left: " + targetLeft + "; img right: " + imgRight + "; img left: " + imgLeft
                                + "target top: " + targetTop + "; target bottom: " + targetBottom + "; img top: " + imgTop + "; img bottom: " + imgBottom);

                        if (targetRight > imgRight && targetLeft < imgLeft && targetTop < imgTop
                                && targetBottom > imgBottom && image2.getTag().equals("recycle")) {
                            debugchannel.setText("lmao"+"target right: " + targetRight + "; target left: " + targetLeft + "; img right: " + imgRight + "; img left: " + imgLeft
                                    + "target top: " + targetTop + "; target bottom: " + targetBottom + "; img top: " + imgTop + "; img bottom: " + imgBottom);
                            hitCheck();
                        }
                        else if(targetRight2 > imgRight && targetLeft2 < imgLeft && targetTop2 < imgTop
                                && targetBottom2 > imgBottom && image2.getTag().equals("recycle"))
                        {
                            image2.setVisibility(View.GONE);
                            score++;
                            points.setText("" + score);
                            count++;
                        }
                    }

                    else if(view.getId()==R.id.imageView3)
                    {
                        int imgRight = image3.getRight()-30;
                        int imgLeft = image3.getLeft()+30;
                        int imgTop = image3.getTop()-relativeposimage3top+100;
                        int imgBottom = image3.getBottom()-relativeposimage3bot-150;

                        //debugchannel.setText("target right: " + targetRight2 + "; target left: " + targetLeft2 + "; img right: " + imgRight + "; img left: " + imgLeft
                             //   + "target top: " + targetTop2 + "; target bottom: " + targetBottom2 + "; img top: " + imgTop + "; img bottom: " + imgBottom);
                        if (targetRight > imgRight && targetLeft < imgLeft && targetTop < imgTop
                                && targetBottom > imgBottom && image3.getTag().equals("trash")) {


                            //debugchannel.setText("yeaaaa");
                            image3.setVisibility(View.GONE);
                            score++;
                            points.setText("" + score);
                            count++;
                        }
                        else if(targetRight2 > imgRight && targetLeft2 < imgLeft && targetTop2 < imgTop
                                && targetBottom2 > imgBottom && image3.getTag().equals("trash"))
                        {
                            //debugchannel.setText("lmaoHA"+"target right: " + targetRight2 + "; target left: " + targetLeft2 + "; img right: " + imgRight + "; img left: " + imgLeft
                                 //    + "target top: " + targetTop2 + "; target bottom: " + targetBottom2 + "; img top: " + imgTop + "; img bottom: " + imgBottom);
                            hitCheck();
                        }
                    }

                    if (count == 3)
                    {
                        hitCheck();
                    }

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