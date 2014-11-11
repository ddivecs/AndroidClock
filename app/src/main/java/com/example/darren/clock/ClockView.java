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
        _clockPaint.setColor(Color.RED);
        _clockPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawRect(0, 0, 100, 100, _clockPaint);

    }
}
