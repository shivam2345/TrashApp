package com.example.shiva.splashscreen;


import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    private RelativeLayout gameFrame;
    private ImageView img, target, target2;
    private TextView debugchannel;
    private ViewGroup rootLayout;
    private int _xDelta;
    private int _yDelta;
    private int screenWidth;
    private int amountToMove;
    private int screenHeight;
    private Handler handler = new Handler();
    private int frameHeight;
    private int frameWidth;
    private Timer timer;

    float orangeY;
    float orangeX;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //rootLayout = (ViewGroup) findViewById(R.id.view_root);
        img = (ImageView) findViewById(R.id.imageView);
        target = (ImageView) findViewById(R.id.target);
        target2 = (ImageView) findViewById(R.id.target2);
        debugchannel = (TextView) findViewById(R.id.textView2);

        //frameHeight = gameFrame.getHeight();
        //frameWidth = gameFrame.getWidth();
        // moveAnimation(img, 500);
        img.setOnTouchListener(new ChoiceTouchListener());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        img.setLayoutParams(layoutParams);
        amountToMove = 500;
        img.animate().translationY(amountToMove);
        img.invalidate();
        // moveAnimation(image2, 1000);
        img.setFocusable(true);
        /*WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        /*if (img.getY() > frameHeight) {
                            orangeY = (float)-100;
                            orangeX = (float) Math.floor(Math.random() * (frameWidth - img.getY()));
                        }
                        img.setX(orangeX);
                        img.setY(orangeY);
                    }
                });

            }
        }, 0, 20);*/
    }

    public void moveAnimation(ImageView imageView, int moveValue)
    {
        Animation img = new TranslateAnimation(Animation.ABSOLUTE, Animation.ABSOLUTE, Animation.ABSOLUTE, moveValue);
        img.setDuration(2000);
        img.setFillAfter(true);
        imageView.startAnimation(img);
        //img.setY(img.getY()+100);
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
                    break;
                case MotionEvent.ACTION_UP:
                    int targetRight = target.getRight();
                    int targetLeft = target.getLeft();
                    int targetTop = target.getTop() - amountToMove;
                    int targetBottom = target.getBottom() - amountToMove;

                    int imgRight = img.getRight();
                    int imgLeft = img.getLeft();
                    int imgTop = img.getTop();
                    int imgBottom = img.getBottom();

                    debugchannel.setText("target right: "+targetRight+ "; target left: "+ targetLeft +"; img right: "+ imgRight+"; img left: "+ imgLeft
                                            + "target top: "+targetTop+ "; target bottom: "+ targetBottom +"; img top: "+ imgTop+"; img bottom: "+ imgBottom);

                    if (targetRight > imgRight && targetLeft < imgLeft && targetTop < imgTop && targetBottom > imgBottom && img.getTag().equals("trash")) {
                        img.setVisibility(View.GONE);
                    }

                    int target2Right = target2.getRight();
                    int target2Left = target2.getLeft();
                    int target2Top = target2.getTop();
                    int target2Bottom = target2.getBottom();

                    if (target2Right > imgRight && target2Left < imgLeft && target2Top < imgTop && target2Bottom > imgBottom && img.getTag().equals("recycle")) {
                        img.setVisibility(View.GONE);
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
}