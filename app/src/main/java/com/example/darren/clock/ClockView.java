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
    private double leftPaddleTop, leftPaddleHeight, rightPaddleTop, rightPaddleHeight, paddleWidth,
        ballWidth, ballHeight, ballTop, ballLeft, numHours, numMins, numSecs;

    Calendar c = Calendar.getInstance();

    public double getLeftPaddleTop() {
        return leftPaddleTop;
    }

    public void setLeftPaddleTop(int leftPaddleTop) {  this.leftPaddleTop = leftPaddleTop;    }

    public double getLeftPaddleHeight() {
        return leftPaddleHeight;
    }

    public void setLeftPaddleHeight(int leftPaddleHeight) {this.leftPaddleHeight = leftPaddleHeight;    }

    public double getRightPaddleTop() {
        return rightPaddleTop;
    }

    public void setRightPaddleTop(int rightPaddleTop) {  this.rightPaddleTop = rightPaddleTop;    }

    public double getRightPaddleHeight() {
        return rightPaddleHeight;
    }

    public void setRightPaddleHeight(int rightPaddleHeight) {this.rightPaddleHeight = rightPaddleHeight;    }

    public double getBallTop() {
        return ballTop;
    }

    public void setBallTop(int ballTop) {   this.ballTop = ballTop;    }

    public double getBallLeft() {
        return ballLeft;
    }

    public void setBallLeft(int ballLeft) {this.ballLeft = ballLeft;}

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
            ballLeft = ((getWidth() - 30) / 30 * currSeconds) + paddleWidth;
        }else{
            ballLeft = (getWidth() - 30) / 30 * (60-currSeconds) + paddleWidth;
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

        //set paddle top locations (their height stays the same)
        //height - 100 (so the top doesn't reach the bottom of the screen)
        leftPaddleTop = (getHeight()-leftPaddleHeight)/12*currHours;
        rightPaddleTop = (getHeight()-rightPaddleHeight)/60*currMins;

        //paddle movement settings
        //This was a test thing so see if we want to keep this in the works
        double hourPaddleMovement = (getHeight()-leftPaddleHeight)/12;
        double minPaddleMovement = (getHeight()-rightPaddleHeight)/60;

        //ball settings
        /*if(currSeconds < 31) {
            //width - ball+paddle width into 30 sections * num of mins passed
            ballLeft = (getWidth() - 30) / 30 * currSeconds;
        }else{
            ballLeft = (getWidth() - 30) / 30 * (60-currSeconds);
        }*/

        ballLeft = (getWidth() - 30)/30 * (30 - Math.abs(30-currSeconds)) + paddleWidth;

        double leftY = leftPaddleTop-leftPaddleHeight/2;
        double rightY = rightPaddleTop - rightPaddleHeight/2;


        ballTop = (leftY-rightY)/(-1*(getWidth() -  2.0*paddleWidth )) * ballLeft + (leftPaddleTop + 40);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //draw the three rectangles
        canvas.drawRect(0, (int)leftPaddleTop, (int)paddleWidth, (int)leftPaddleTop + (int)leftPaddleHeight, _clockPaint);
        canvas.drawRect(getWidth()-(int)paddleWidth, (int)rightPaddleTop, getWidth(), (int)rightPaddleTop + (int)rightPaddleHeight, _clockPaint);
        canvas.drawRect((int)ballLeft, (int)ballTop, (int)ballLeft + (int)ballWidth, (int)ballTop + (int)ballHeight, _clockPaint);
        //canvas.drawText((c.get(Calendar.SECOND))+"", ballLeft, ballTop, ballLeft + ballWidth, ballTop + ballHeight, _clockPaint);
    }
}
