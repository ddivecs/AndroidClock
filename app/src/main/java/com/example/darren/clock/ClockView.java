package com.example.darren.clock;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Matt on 11/11/2014.
 */
public class ClockView extends View {

    private Paint _clockPaint = new Paint();
    private float leftPaddleTop, leftPaddleHeight, rightPaddleTop, rightPaddleHeight, paddleWidth,
        ballWidth, ballHeight, ballTop, ballLeft, numHours, numMins, numSecs;

    //all the settings that are able to be changed in settings
    private boolean textColor, backgroundColorChange, pongAnimation;


    Calendar c = Calendar.getInstance();

    public void setTextColor(Boolean _textColor){
        textColor = _textColor;
    }

    public void setBackgroundColorChange(Boolean bgColor){
        backgroundColorChange = bgColor;
    }

    public void setPongAnimation(Boolean pongAni){
        pongAnimation = pongAni;
    }

    public Boolean getPongAnimation() { return pongAnimation; }

    public Boolean getBackgroundColorChange() { return backgroundColorChange; }

    public ClockView(Context context) {
        super(context);
        init(null, 0);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ClockView,
                0, 0);

        try{
            pongAnimation = a.getBoolean(R.styleable.ClockView_pongAnimation, false);
            textColor = a.getBoolean(R.styleable.ClockView_textColor, false);
            backgroundColorChange = a.getBoolean(R.styleable.ClockView_backgroundColorChange, false);
        }finally{
            a.recycle();
        }
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
        ballHeight = 40;


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

        //Move the paddles up or down
        leftPaddleTop = (getHeight()-leftPaddleHeight)/(numHours/2) * (numHours/2 - Math.abs(numHours/2 - currHours)) ;
        rightPaddleTop = (getHeight()-rightPaddleHeight)/(numMins/2)*(numMins/2-Math.abs(numMins/2-currMins));

        //Calculate the midpoints of the left and right paddles
        double leftY = leftPaddleTop+leftPaddleHeight/2;
        double rightY = rightPaddleTop + rightPaddleHeight/2;

        //Change ball settings
        ballLeft = (getWidth() - numSecs/2)/(numSecs/2) * (numSecs/2 - Math.abs(numSecs/2-currSeconds)) + paddleWidth;
        ballTop = (float)(leftY-rightY)/(-1*(getWidth() -  2*paddleWidth )) * ballLeft + (leftPaddleTop + 40);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);


        if(!backgroundColorChange || c.get(Calendar.AM_PM) == Calendar.AM) {
            _clockPaint.setColor(Color.WHITE);

        }
        else{
            _clockPaint.setColor(Color.BLACK);

        }

        canvas.drawRect(0, 0, getWidth(), getHeight(), _clockPaint);

        if(pongAnimation){


            int currSeconds = c.get(Calendar.SECOND);
            int currMins = c.get(Calendar.MINUTE);
            int currHours = c.get(Calendar.HOUR);

            int colorR = (int)((numHours/2 - Math.abs(numHours/2 - currHours)) * 255/(numHours/2));
            int colorG = (int)((numMins/2 - Math.abs(numMins/2-currMins)) * 255/(numMins/2));
            int colorB = (int)((numSecs/2 - Math.abs(numSecs/2-currSeconds)) *255/(numSecs/2));




            _clockPaint.setColor(Color.RED);

            //draw the paddles
            canvas.drawRect(0, (int)leftPaddleTop, (int)paddleWidth, (int)leftPaddleTop + (int)leftPaddleHeight, _clockPaint);
            canvas.drawRect(getWidth() - (int) paddleWidth, (int) rightPaddleTop, getWidth(), (int) rightPaddleTop + (int) rightPaddleHeight, _clockPaint);


            _clockPaint.setTextSize(ballHeight);
            if(textColor)
                _clockPaint.setColor( Color.rgb(colorR,colorG,colorB));
            else if(!backgroundColorChange || c.get(Calendar.AM_PM) == Calendar.AM)
                _clockPaint.setColor(Color.BLACK);
            else
                _clockPaint.setColor(Color.WHITE);

            //Add numbers to paddles and print "ball"
            if(c.get(Calendar.HOUR) == 0){
                canvas.drawText("12", 0, (float) leftPaddleTop + leftPaddleHeight + ballHeight, _clockPaint);
            }else {
                canvas.drawText(c.get(Calendar.HOUR) + "", 0, (float) leftPaddleTop, _clockPaint);
            }

            if(c.get(Calendar.MINUTE) == 0){
                canvas.drawText(c.get(Calendar.MINUTE)+"", getWidth()-ballHeight, rightPaddleTop + rightPaddleHeight + ballHeight, _clockPaint);
            }else {
                canvas.drawText(c.get(Calendar.MINUTE)+"", getWidth()-ballHeight, rightPaddleTop, _clockPaint);
            }

            canvas.drawText((c.get(Calendar.SECOND))+"", ballLeft, ballTop+ballHeight, _clockPaint);
        }
    }
}
