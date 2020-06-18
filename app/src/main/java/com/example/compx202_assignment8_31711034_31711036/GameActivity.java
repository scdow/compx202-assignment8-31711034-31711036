package com.example.compx202_assignment8_31711034_31711036;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends AppCompatActivity {
    public class GraphicView extends View {
        private float x;
        private float y;
        private float speedX;
        private float speedY;
        private int radius;
        private Paint paint;

        private int targetR;
        private int obsR;

        private float targetX;
        private float targetY;

        private float[] obsLoseX;
        private float[] obsLoseY;
        private int speedObsIn;

        private float[] obsInX;
        private float[] obsInY;


        GestureDetector gestureDetector;

        public GraphicView(Context context) {
            super(context);
            x=300;
            y=1300;
            speedX = 0;
            speedY = 0;
            radius = 30;

            targetX=500;
            targetY=200;
            targetR=70;

            obsR=40;
            obsInX= new float[]{40, 600};
            obsInY= new float[]{100,1200};
            speedObsIn = 15;

            paint = new Paint();
            gestureDetector = new GestureDetector(context, new MyGestureListener());
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            x+=speedX;
            y+=speedY;
            //            make ball always stay on screen
            if (x > getWidth()) {
                x = getWidth();
            } else if (x < 0) {
                x = 0;
            }

            if (y > getHeight()) {
                y = getHeight();
            } else if (y < 0) {
                y = 0;
            }
            paint.setColor(getColor(R.color.ball));
            canvas.drawCircle(x, y, radius,paint);

            paint.setColor(getColor(R.color.target));
            canvas.drawCircle(targetX,targetY,targetR,paint);

            obsInX[0]+=speedObsIn;
            obsInX[1]-=speedObsIn;
            obsInY[0]+=speedObsIn;
            obsInY[1]-=speedObsIn;
//            int a = speedObsIn;
            for(int p=0;p<2;p++){
//                obsInX[p]+=speedObsIn;
//                obsInY[p]+=speedObsIn;
                if (obsInX[p] > getWidth()) {
//                    obsInY[p]=getHeight()-((obsInX[p]+speedObsIn)%getHeight());
                    obsInX[p]-=2*speedObsIn;
                } else if (obsInX[p] < 0) {
//                    obsInY[p] = getHeight()-((-obsInY[p]+speedObsIn)%getHeight());
                    obsInX[p]+=2*speedObsIn;
                }
                if (obsInY[p] > getHeight()) {
//                    obsInY[p]=getHeight()-((obsInX[p]+speedObsIn)%getHeight());
                    obsInY[p]-=2*speedObsIn;
                } else if (obsInY[p] < 0) {
//                    obsInY[p] = getHeight()-((-obsInY[p]+speedObsIn)%getHeight());
                    obsInY[p]+=2*speedObsIn;
                }

                paint.setColor(Color.GREEN);
                canvas.drawCircle(obsInX[p],obsInY[p],obsR,paint);
            }

//            for(int p=0;p<2;p++) {
//                paint.setColor(Color.BLUE);
//                canvas.drawCircle(obsInX[p],obsInY[p],obsR,paint);
//            }

            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (gestureDetector.onTouchEvent(event)){
                return true;
            }
            /*comment to cancel ball follow finger*/
//            x = event.getX();
//            y = event.getY();
            speedX=0;
            speedY=0;
            return super.onTouchEvent(event);
        }

        public class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("MYLOG", "FLING");
                /*comment to cancel ball follow finger*/
//                x = e2.getX();
//                y = e2.getY();
                if (e1.getX() < e2.getX()){
                    Log.i("MYLOG", "Move right");
                }
                else{
                    Log.i("MYLOG", "Move left");
                }
                if (e1.getY() < e2.getY()){
                    Log.i("MYLOG", "Move down");
                }
                else{
                    Log.i("MYLOG", "Move up");
                }
                speedX=velocityX/100;
                speedY=velocityY/100;
                return true;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set stick immersive full-screen mode
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        GraphicView gv = new GraphicView(this);
        ConstraintLayout rootView = (ConstraintLayout)findViewById(R.id.rootView);
        rootView.addView(gv);
    }
}


