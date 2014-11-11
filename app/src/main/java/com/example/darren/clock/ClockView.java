package com.example.darren.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Matt on 11/11/2014.
 */
public class ClockView extends View{

    private Paint _clockPaint = new Paint();
    private int leftPaddleTop, leftPaddleHeight, rightPaddleTop, rightPaddleHeight, paddleWidth,
        ballWidth, ballHeight, ballTop, ballLeft, numHours, numMins, numSecs;

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

        //paddle settings
        leftPaddleTop = 0;
        leftPaddleHeight = 100;

        rightPaddleTop = 0;
        rightPaddleHeight = 100;

        paddleWidth = 10;

        //ball settings
        ballLeft = paddleWidth;
        ballTop = 0;
        ballWidth = 15;
        ballHeight = 15;

        //paint settings
        _clockPaint.setColor(Color.RED);
        _clockPaint.setAntiAlias(true);
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
