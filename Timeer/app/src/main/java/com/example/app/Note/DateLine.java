package com.example.app.Note;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xxx on 14-6-18.
 */
public class DateLine extends TextView {

    private Paint ePaint = new Paint();

    public DateLine(Context context) {
        super(context);
        // TODO 自动生成的构造函数存根
    }

    public DateLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO 自动生成的构造函数存根
        this.ePaint.setColor(Color.BLACK);
        this.ePaint.setStyle(Paint.Style.STROKE);
        this.ePaint.setStrokeWidth((float)2.0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO 自动生成的方法存根
        canvas.drawLine(0.0F, 50.0F, getWidth(), 50.0F, this.ePaint);
        super.onDraw(canvas);
    }

}