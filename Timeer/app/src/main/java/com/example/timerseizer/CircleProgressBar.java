
package com.example.timerseizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CircleProgressBar extends View {
    private double maxProgress = 100;//最大进度
    private double progress = 0;//当前进度
    private float progressStrokeWidth = 60;//线宽
    private String remind="25:00";
    // 画圆所在的矩形区域
    RectF oval;
    Paint paint;

    public CircleProgressBar(Context context) {
        super(context);
        // TODO 自动生成的构造函数存根
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO 自动生成的构造函数存根
        oval = new RectF();
        paint = new Paint();
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO 自动生成的构造函数存根
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        float centerx = (float)(getWidth()/2.0); //获取圆心的x坐标

        paint = new Paint();
        float radius = (Math.min(centerx,getHeight()/2+getTopPaddingOffset()) - progressStrokeWidth/2-10); //圆环的半径
        float centery=getTopPaddingOffset()+radius+35;
        paint.setColor(Color.WHITE);//(roundColor); //设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(progressStrokeWidth); //设置圆环的宽度
        paint.setAntiAlias(true);  //消除锯齿
        paint.setShadowLayer(5,5,5,Color.GRAY);

        canvas.drawCircle(centerx, centery, radius, paint); //画出圆环

        float textsize=(float)(radius*0.6);
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint.setFakeBoldText(true);
        textPaint.setSubpixelText(true);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(textsize);
        textPaint.setShadowLayer(5,2,2,Color.rgb(185,185,185));
        int R=0,G=0,B=0;
        R=(int)(200 * progress / maxProgress);
        textPaint.setColor(Color.rgb(R,G,B));
        float textHeight =  textPaint.measureText(remind)/8;
        float stringSizeWidth = textPaint.measureText(remind)/2;
        canvas.drawText(remind,(float)centerx-stringSizeWidth,(float)centery+textHeight,textPaint);


        SweepGradient sweepGradient = new SweepGradient(centerx,centery,Color.rgb(146,0,146),Color.rgb(255,114,255));
        Matrix m=new Matrix();
        m.setRotate(270, centerx, centery);
        sweepGradient.setLocalMatrix(m);

        paint.setStrokeWidth(progressStrokeWidth); //设置圆环的宽度
        paint.setShader(sweepGradient);
        RectF oval = new RectF(centerx - radius, centery - radius, centerx
                + radius, centerx + radius);

        paint.setStyle(Paint.Style.STROKE);


        canvas.drawArc(oval, -90, (float)(360 * progress / maxProgress), false, paint);


        //根据进度画圆弧  绘制白色圆圈，即进度条背景
    }

    public double getMaxProgress(){
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress){
        this.maxProgress = maxProgress;
    }

    public void setProgress(double progress){
        this.progress = progress;
        this.invalidate();
    }
    public void setText(String s){
        remind=s;
        this.postInvalidate();
    }
    public void setTextnotRefresh(String s){
        remind=s;
    }
    public void setTime(long millisInFuture){
        setText(new SimpleDateFormat("mm:ss").format(new Date(
                millisInFuture)));
    }
    public void setTimenotRefresh(long m){
        setTextnotRefresh(new SimpleDateFormat("mm:ss").format(new Date(
                m)));
    }
    public void setProgressNotInUiThread(double progress){
        this.progress = progress;
        this.postInvalidate();
    }

}

