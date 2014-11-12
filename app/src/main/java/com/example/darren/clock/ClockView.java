package com.example.darren.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Matt on 11/11/2014.
 */
public class ClockView extends View{

    private Paint _clockPaint = new Paint();
    private int leftPaddleTop, leftPaddleHeight, rightPaddleTop, rightPaddleHeight, paddleWidth,
        ballWidth, ballHeight, ballTop, ballLeft, numHours, numMins, numSecs;

    Calendar c = Calendar.getInstance();

    public int getLeftPaddleTop() {
        return leftPaddleTop;
    }

    public void setLeftPaddleTop(int leftPaddleTop) {
        this.leftPaddleTop = leftPaddleTop;
//        invalidate();
//        requestLayout();
    }

    public int getLeftPaddleHeight() {
        return leftPaddleHeight;
    }

    public void setLeftPaddleHeight(int leftPaddleHeight) {
        this.leftPaddleHeight = leftPaddleHeight;
//        invalidate();
//        requestLayout();
    }

    public int getRightPaddleTop() {
        return rightPaddleTop;
    }

    public void setRightPaddleTop(int rightPaddleTop) {
        this.rightPaddleTop = rightPaddleTop;

//        invalidate();
//        requestLayout();

    }

    public int getRightPaddleHeight() {
        return rightPaddleHeight;
    }

    public void setRightPaddleHeight(int rightPaddleHeight) {
        this.rightPaddleHeight = rightPaddleHeight;
//        invalidate();
//        requestLayout();
    }

    public int getBallTop() {
        return ballTop;
    }

    public void setBallTop(int ballTop) {
        this.ballTop = ballTop;
//        invalidate();
//        requestLayout();
    }

    public int getBallLeft() {
        return ballLeft;
    }

    public void setBallLeft(int ballLeft) {
        this.ballLeft = ballLeft;
//        invalidate();
//        requestLayout();
    }

    public ClockView(Context context) {
        super(context);
        init(null, 0);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr){
        numHours = 12;
        numMins = 60;
        numSecs = 60;

        int currSeconds = c.get(Calendar.SECOND);
        int currMins = c.get(Calendar.MINUTE);
        int currHours = c.get(Calendar.HOUR);

//paddle settings
        
        leftPaddleHeight = 100;
        rightPaddleHeight = 100;
        paddleWidth = 10;
        leftPaddleTop = (getHeight()-leftPaddleHeight)/12*currHours;
        rightPaddleTop = (getHeight()-rightPaddleHeight)/60*currMins;

        //ball settings
        ballTop = 0;
        ballWidth = 20;
        ballHeight = 20;


        if(currMins < 31) {
            //width - ball+paddle width into 30 sections * num of mins passed
            ballLeft = (getWidth() - 30) / 30 * currSeconds;
        }else{
            ballLeft = (getWidth() - 30) / 30 * (60-currSeconds);
        }

        //paint settings
        _clockPaint.setColor(Color.RED);
        _clockPaint.setAntiAlias(true);
    }

    //updates all the values we need to update
    public void update(){

        //get current time
        c = Calendar.getInstance();
        int currSeconds = c.get(Calendar.SECOND);
        int currMins = c.get(Calendar.MINUTE);
        int currHours = c.get(Calendar.HOUR);

        leftPaddleTop = (getHeight()-leftPaddleHeight)/12*currHours;
        rightPaddleTop = (getHeight()-rightPaddleHeight)/60*currMins;

        //paddle movement settings
        int hourPaddleMovement = (getHeight()-leftPaddleHeight)/12;
        int minPaddleMovement = (getHeight()-rightPaddleHeight)/60;

        //ball settings
        if(currMins < 31) {
            //width - ball+paddle width into 30 sections * num of mins passed
            ballLeft = (getWidth() - 30) / 30 * currSeconds;
        }else{
            ballLeft = (getWidth() - 30) / 30 * (60-currSeconds);
        }
    }

    @Override
    public void onDraw(Canvas canvas){


        super.onDraw(canvas);

        //draw the three rectangles
        canvas.drawRect(0, leftPaddleTop, paddleWidth, leftPaddleTop + leftPaddleHeight, _clockPaint);
        canvas.drawRect(getWidth()-paddleWidth, rightPaddleTop, getWidth(), rightPaddleTop + rightPaddleHeight, _clockPaint);
        canvas.drawRect(ballLeft, ballTop, ballLeft + ballWidth, ballTop + ballHeight, _clockPaint);

    }
}
