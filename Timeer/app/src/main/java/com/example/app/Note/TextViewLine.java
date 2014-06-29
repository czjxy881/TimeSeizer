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
public class TextViewLine extends TextView {

    private Paint ePaint = new Paint();

    public TextViewLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO 自动生成的构造函数存根
        this.ePaint.setColor(Color.BLACK);
        this.ePaint.setStyle(Paint.Style.STROKE);
        this.ePaint.setStrokeWidth((float)2.0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO 自动生成的方法存根
        super.onDraw(canvas);
        int i=getLineCount();
        for (int j = 0; ; ++j)
        {
            if (j >= i)
            {
                super.onDraw(canvas);
                return;
            }
            float[] arrayOfFloat = new float[4];
            arrayOfFloat[0] = 15.0F;
            arrayOfFloat[1] = ((j + 1) * getLineHeight()-10.0F);
            arrayOfFloat[2] = (-20 + getWidth());
            arrayOfFloat[3] = ((j + 1) * getLineHeight()-10.0F);
            canvas.drawLines(arrayOfFloat, this.ePaint);
        }
    }

}