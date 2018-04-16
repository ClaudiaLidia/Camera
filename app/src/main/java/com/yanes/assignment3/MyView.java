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
    Paint mPaint, mcheck, mrec, emogi, eyes, mouse;
    ArrayList<em> emo;
    DisplayMetrics dn;
    private int currentWidth;
    private int currentHeight;
    boolean touched = false;
    private int dpSize;
    private int color;
    private String type;
    private   int radius;

    ArrayList<po> posi= new ArrayList<>();

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

        mcheck = new Paint();
        mcheck.setColor(Color.GREEN);
        mcheck.setStyle(Paint.Style.STROKE);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        emogi = new Paint();
        emogi.setColor(Color.YELLOW);
        emogi.setStyle(Paint.Style.FILL_AND_STROKE);

        eyes = new Paint();
        eyes.setColor(Color.BLACK);
        eyes.setStyle(Paint.Style.FILL);

        mouse = new Paint();
        mouse.setColor(Color.RED);
        mouse.setStyle(Paint.Style.STROKE);

        int dpSize = 10;
        dn = getResources().getDisplayMetrics();
        float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dn);

        mPaint.setStrokeWidth(strokeWidth);
        mcheck.setStrokeWidth(strokeWidth);
        mrec.setStrokeWidth(strokeWidth);

        emo = new ArrayList<em>();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        currentWidth = w ;
        currentHeight = h;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int size = posi.size();

        for (int i = 0; i < size; i++) {
            po ball = posi.get(i);
            xDown = ball.xDown;
            yDown = ball.yDown;
            xUp = ball.xUp;
            yUp = ball.yUp;
            type = ball.type;
            dpSize = ball.dpSize;
            mPaint.setColor(ball.color);
            mrec.setColor(ball.color);
            emogi.setColor(ball.color);
            mcheck.setColor(ball.color);
            float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dn);

            mPaint.setStrokeWidth(strokeWidth);
            mrec.setStrokeWidth(strokeWidth * 10);
            mcheck.setStrokeWidth(strokeWidth);

            radius = dpSize * 10;
            if(ball.color== Color.BLACK){
                eyes.setColor(Color.WHITE);
                mouse.setColor(Color.RED);
            }else if(ball.color==Color.RED){
                mouse.setColor(Color.BLACK);
                eyes.setColor(Color.BLACK);
            }
            else{
                eyes.setColor(Color.BLACK);
                mouse.setColor(Color.RED);
            }

            if (type == "Line") {
                canvas.drawLine(xDown, yDown, xUp, yUp, mPaint);
            } else if (type == "Rectangle") {
                canvas.drawRect(xDown - (strokeWidth * 10), yDown - (strokeWidth * 10) / 2, xDown + (strokeWidth * 10), yDown + (strokeWidth * 10) / 2, mrec);    //rectangle
            } else if (type == "Point") {
                canvas.drawPoint(xDown, yDown, mPaint);
            } else if (type == "Check") {
                canvas.drawLine(xDown + (strokeWidth) * 10 / 9, yDown + (strokeWidth) * 10 / 6, xDown + (strokeWidth) * 10 / 2, yDown - (strokeWidth) * 10 / 2, mcheck);
                canvas.drawLine(xDown - (strokeWidth) * 10 / 6, yDown - (strokeWidth) * 10 / 6, xDown + (strokeWidth) * 10 / 9, yDown + (strokeWidth) * 10 / 6, mcheck);
            } else if (type == "Oval") {
                canvas.drawOval(xDown, yDown, xUp, yUp, mPaint);
            }else if (type == "Emo") {
                int size1 = emo.size();
                for (int ii = 0; ii < size1; ii++) {
                    em e2 = emo.get(ii);
                    canvas.drawCircle(xDown, yDown, 70, emogi);
                    canvas.drawCircle(xDown - 65, yDown - 65, radius / 3, eyes);
                    canvas.drawCircle(xDown + 65, yDown - 65, radius / 3, eyes);
                    canvas.drawCircle(xDown, yDown, radius / 10, eyes);
                    canvas.drawCircle(xDown, yDown + 75, radius / 5, mouse);


                }
            }else if(type == "X"){
                canvas.drawLine(xDown-(strokeWidth)*10/2, yDown+(strokeWidth)*10/2, xDown +(strokeWidth)*10/2, yDown-(strokeWidth)*10/2 , mcheck);
                canvas.drawLine(xDown-(strokeWidth)*10/2, yDown-(strokeWidth)*10/2, xDown+(strokeWidth)*10/2, yDown+(strokeWidth)*10/2, mcheck);
            }

        }
    }
    public void delete(){
       posi.removeAll(posi);
        invalidate();
    }
    public void addB2() {
        em e1 = new em(100,10 );
        emo.add(e1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dpSize = MainActivity.thicknesses;
        type = MainActivity.type;
        color = MainActivity.color;
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
        po po1 = new po(xDown, yDown,xUp, yUp,type, color, dpSize);
        posi.add(po1);
        invalidate();
        return true;
    }
    private class po {
        private  float xDown;
        private  float  yDown;
        private int dpSize;
        private  float xUp;
        private  float yUp;
        private String type;
        private  int color;

        public po(float xDown, float yDown, float xUp, float yUp, String type, int color, int dpSize) {
            this.xDown = xDown;
            this.yDown = yDown;
            this.xUp = xUp;
            this.yUp = yUp;
            this.dpSize = dpSize;
            this.color = color;
            this.type = type;
        }


    }
    private class em{

        private int dpSize1;
        private int dpSize2;
        em(int dpSize1, int dpSize2) {
            float strokeWidth2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize1, dn);
            float strokeWidth3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize2, dn);

            emogi.setStrokeWidth(strokeWidth2);
            mouse.setStrokeWidth(strokeWidth3);
        }
    }
}
