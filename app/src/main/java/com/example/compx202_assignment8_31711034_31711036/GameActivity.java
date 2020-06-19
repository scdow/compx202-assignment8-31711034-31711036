package com.example.compx202_assignment8_31711034_31711036;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends AppCompatActivity {
    private int score;

    public class GraphicView extends View {
        boolean judgeLose=false;
        private float x;
        private float y;
        private float speedX;
        private float speedY;
        private int radius;
        private Paint paint;
        private float xMax;
        private float yMax;

        private int targetR;
        private int obsR;
        private int loseR;

        private float targetX;
        private float targetY;

        private float[] obsLoseX;
        private float[] obsLoseY;
        private  int[] speedObsLose;

        private float[] obsInX;
        private float[] obsInY;
        private int[] speedObsInX;
        private int[] speedObsInY;

        private double[] distanceIn = new double[4];
        private double[] distanceEnd = new double[2];
        private double distanceTarget;

        GestureDetector gestureDetector;

//        TextView scoreText = (TextView)findViewById(R.id.scoreText);
//        String scoreStr = String.valueOf(score);

        public GraphicView(Context context) {
            super(context);
            x=300;
            y=1300;
            speedX = 0;
            speedY = 0;
            radius = 35;

            targetX=500;
            targetY=200;
            targetR=60;

            obsR=40;
            obsInX= new float[]{40, 1000,400,800};
            obsInY= new float[]{100,1800,1500,500};
            speedObsInX =new int[]{2,5,3,7};
            speedObsInY =new int[]{5,2,7,3};

            loseR=30;
            obsLoseX=new float[]{70,500};
            obsLoseY=new float[]{150,1000};
            speedObsLose = new int[]{2,2};

            paint = new Paint();
            gestureDetector = new GestureDetector(context, new MyGestureListener());
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            paint.setTextSize(50);
            paint.setColor(getColor(R.color.ball));
            canvas.drawText(""+score, 250, 55, paint);

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

//            int a0x,a0y,a1x,a1y;
//            a0x=a0y=a1x=a1y=speedObsIn;

//            obsInX[0]+=speedObsIn;
//            obsInY[0]+=speedObsIn;
//            obsInX[1]-=speedObsIn;
//            obsInY[1]-=speedObsIn;
//            obsInX[2]+=speedObsIn;
//            obsInY[2]-=speedObsIn;
//            obsInX[3]-=speedObsIn;
//            obsInY[3]+=speedObsIn;
//            TextView scoreText = (TextView)findViewById(R.id.scoreText);
            for(int p=0;p<4;p++){

                obsInX[p]+=speedObsInX[p];
                obsInY[p]+=speedObsInY[p];

                distanceIn[p] = Math.sqrt(((x - obsInX[p]) * (x - obsInX[p])) + ((y - obsInY[p]) * (y - obsInY[p])));
                if (distanceIn[p] < radius + obsR){
                    score+=1;
                    Log.i("MYSCORE", "Score now: "+score);

                    if(speedX/speedObsInX[p]>0){
                        speedX=-speedX;
                    }
                    else if(speedX/speedObsInX[p]<=0){
//                        speedX=-speedX;
                        speedObsInX[p]=-speedObsInX[p];
                    }
                    if(speedY/speedObsInY[p]>0){
                        speedY=-speedY;
                    }
                    else if(speedY/speedObsInY[p]<=0){
                        speedY=-speedY;
//                        speedObsInY[p]=-speedObsInY[p];
                    }
                }

                if (obsInX[p] > getWidth()) {
//                    obsInY[p]=getHeight()-((obsInX[p]+speedObsIn)%getHeight());
                    obsInX[p]=0;
                } else if (obsInX[p] < 0) {
//                    obsInY[p] = getHeight()-((-obsInY[p]+speedObsIn)%getHeight());
                    obsInX[p]=getWidth();
                }
                if (obsInY[p] > getHeight()) {
//                    obsInY[p]=getHeight()-((obsInX[p]+speedObsIn)%getHeight());
                    obsInY[p]=0;
                } else if (obsInY[p] < 0) {
//                    obsInY[p] = getHeight()-((-obsInY[p]+speedObsIn)%getHeight());
                    obsInY[p]=getHeight();
                }

                paint.setColor(getColor(R.color.colorAccent));
                canvas.drawCircle(obsInX[p],obsInY[p],obsR,paint);
            }

            for(int p=0;p<2;p++){
                obsLoseX[p]+=speedObsLose[p];
                obsLoseY[p]+=speedObsLose[p];

                distanceEnd[p] = Math.sqrt(((x - obsLoseX[p]) * (x - obsLoseX[p])) + ((y - obsLoseY[p]) * (y - obsLoseY[p])));
                if (distanceEnd[p] < radius + loseR){
                    Log.i("MYSCORE", "Lose: ");
                    judgeLose=true;
//                    Intent intent = new Intent(this, LoseActivity.class);
//                    startActivity(intent);
                    showGameLoseDialog();
                }

                float[] xBackup = new float[2];
                float[] yBackup = new float[2];
                xBackup[p] = obsLoseX[p];
                yBackup[p] = obsLoseY[p];

                // Handle the cases where the ball goes off the screen
                if (obsLoseX[p] > getWidth()) {
                    obsLoseX[p] = getWidth();
                    obsLoseX[p] = xBackup[p];
                    //Speed reverse
                    speedObsLose[p] = 0 - speedObsLose[p];
                } else if (obsLoseX[p] < 0) {
                    obsLoseX[p] = 0;
                    obsLoseX[p] = xBackup[p];
                    //Speed reverse
                    speedObsLose[p] = 0-speedObsLose[p];
                }

                if (obsLoseY[p] > getHeight()) {
                    obsLoseY[p] = getHeight();
                    obsLoseY[p] = yBackup[p];
                    //Speed reverse
                    speedObsLose[p] = 0 - speedObsLose[p];
                } else if (obsLoseY[p] < 0) {
                    obsLoseY[p] = 0;
                    obsLoseY[p] = yBackup[p];
                    //Speed reverse
                    speedObsLose[p] = 0 - speedObsLose[p];
                }

                paint.setColor(getColor(R.color.obsLose));
                canvas.drawCircle(obsLoseX[p],obsLoseY[p],loseR,paint);
            }

            distanceTarget = Math.sqrt(((x - targetX) * (x - targetX)) + ((y - targetY) * (y - targetY)));
            if (distanceTarget < radius + targetR){
                Log.i("MYSCORE", "Win: ");
//                    Intent intent = new Intent(this, LoseActivity.class);
//                    startActivity(intent);
                    showGameWinDialog();
            }

            if((distanceTarget > radius + targetR)&&(judgeLose==false)){
                invalidate();
            }
        }

        // Use onSizeChange to get information on screen size and set the ball on a different initial position
        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            xMax=w;
            yMax=h;
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
                speedX=velocityX/200;
                speedY=velocityY/200;
                return true;
            }
        }
    }

    private void showGameLoseDialog() {
            new AlertDialog.Builder(this).setTitle("Sorry").setMessage("You Lose.  " +"Score: "+score+" to be 0")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    finish();
                                }
                            }).show();
    }

    private void showGameWinDialog() {
        new AlertDialog.Builder(this).setTitle("Congratulations").setMessage("You Win!  "+"Score: "+score)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                finish();
                            }
                        }).show();
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

//        TextView scoreText = (TextView)findViewById(R.id.scoreText);
//        String scoreStr = String.valueOf(score);
//        scoreText.setText(scoreStr);

        GraphicView gv = new GraphicView(this);
        ConstraintLayout rootView = (ConstraintLayout)findViewById(R.id.rootView);
        rootView.addView(gv);

//        TextView scoreText = (TextView)findViewById(R.id.scoreText);
//        String scoreStr = String.valueOf(score);
//        scoreText.setText(scoreStr);
    }
}


