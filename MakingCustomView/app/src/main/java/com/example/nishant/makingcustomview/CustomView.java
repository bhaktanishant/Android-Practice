package com.example.nishant.makingcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Nishant on 2/7/2017.
 */

public class CustomView extends View{
    final Paint mPaint = new Paint();

    public CustomView(Context context){
        super(context);
        mPaint.setColor(Color.CYAN);
        mPaint.setTextSize(30);
    }

    protected void onDraw (Canvas canvas){
        super.onDraw(canvas);
        setBackgroundColor(Color.GRAY);
        canvas.drawText("Custom text", 100, 100, mPaint);
        invalidate();
    }
}