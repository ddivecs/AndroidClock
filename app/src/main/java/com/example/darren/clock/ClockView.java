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
    }

    public int getLeftPaddleHeight() {
        return leftPaddleHeight;
    }

    public void setLeftPaddleHeight(int leftPaddleHeight) {
        this.leftPaddleHeight = leftPaddleHeight;
    }

    public int getRightPaddleTop() {
        return rightPaddleTop;
    }

    public void setRightPaddleTop(int rightPaddleTop) {
        this.rightPaddleTop = rightPaddleTop;
    }

    public int getRightPaddleHeight() {
        return rightPaddleHeight;
    }

    public void setRightPaddleHeight(int rightPaddleHeight) {
        this.rightPaddleHeight = rightPaddleHeight;
    }

    public int getBallTop() {
        return ballTop;
    }

    public void setBallTop(int ballTop) {
        this.ballTop = ballTop;
    }

    public int getBallLeft() {
        return ballLeft;
    }

    public void setBallLeft(int ballLeft) {
        this.ballLeft = ballLeft;
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

//get current time

        ballWidth = 20;
        ballHeight = 20;


        //paint settings
        _clockPaint.setColor(Color.RED);
        _clockPaint.setAntiAlias(true);
    }

    public void update(){
        int currSeconds = c.get(Calendar.SECOND);
        int currMins = c.get(Calendar.MINUTE);
        int currHours = c.get(Calendar.HOUR);


        //paddle settings
        leftPaddleTop = (getHeight()-leftPaddleHeight)/12*currHours;
        leftPaddleHeight = 100;

        rightPaddleTop = (getHeight()-rightPaddleHeight)/60*currMins;
        rightPaddleHeight = 100;

        //paddle movement settings
        int hourPaddleMovement = (getHeight()-leftPaddleHeight)/12;
        int minPaddleMovement = (getHeight()-rightPaddleHeight)/60;




        paddleWidth = 10;

        //ball settings
        ballLeft = paddleWidth;
        ballTop = 0;

    }

    @Override
    public void onDraw(Canvas canvas){


        super.onDraw(canvas);


        System.out.println("ballLeft = "+ballLeft);
        System.out.println("rightPaddleTop = "+ rightPaddleTop);

        //draw the three rectangles
        canvas.drawRect(0, leftPaddleTop, paddleWidth, leftPaddleTop + leftPaddleHeight, _clockPaint);
        canvas.drawRect(getWidth()-paddleWidth, rightPaddleTop, getWidth(), rightPaddleTop + rightPaddleHeight, _clockPaint);
        canvas.drawRect(ballLeft, ballTop, ballLeft + ballWidth, ballTop + ballHeight, _clockPaint);

    }
}
