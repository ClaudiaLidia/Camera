package com.yanes.assignment3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by claud on 4/15/2018.
 */

public class MyView extends View {
    private float xDown , yDown , xUp , yUp;
    Paint mPaint, mrec;
    DisplayMetrics dn;
    private int currentWidth;
    private int currentHeight;
    boolean touched = false;

    private String type;


    public MyView() {
        super(null);
        setup(null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup(context);
    }
    private void setup(Context context) {

        mrec = new Paint();
        mrec.setColor(Color.RED);
        mrec.setStyle(Paint.Style.FILL);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        int dpSize = 10;
        dn = getResources().getDisplayMetrics();
        float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dn);

        mPaint.setStrokeWidth(strokeWidth);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        currentWidth = w;
        currentHeight = h;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int dpSize = 10;

        float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dn);

        if (type == "Line") {
            canvas.drawLine(xDown, yDown, xUp, yUp, mPaint);
        }else if(type == "Rectangle") {
            canvas.drawRect(xDown - (strokeWidth * 10) , yDown - (strokeWidth * 10) / 2, xDown + (strokeWidth * 10) , yDown + (strokeWidth * 10) / 2, mrec);    //rectangle
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        type = MainActivity.type;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                yDown = event.getY();
                xUp = event.getX();
                yUp = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                xUp = event.getX();
                yUp = event.getY();
                touched = true;
                break;
        }

        invalidate();
        return true;
    }
}
