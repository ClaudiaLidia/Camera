package com.yanes.assignment3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by claud on 4/15/2018.
 */

public class MyView extends View {

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

    }
}
