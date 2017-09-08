package com.whieenz.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by heziwen on 2017/9/8.
 * 作用：
 */

public class PView extends View{

    private int min = Color.parseColor("#FF7F00");
    private int max = Color.parseColor("#00B2EE");
    private int width;
    private int height;
    private double progress;
    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    private Paint mPaint1 = new Paint();
    Paint textPaint = new Paint();          // 创建画笔

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.GRAY);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setStrokeWidth(2f);         //设置画笔宽度为10px

        mPaint1.setColor(Color.BLUE);       //设置画笔颜色
        mPaint1.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint1.setStrokeWidth(2f);         //设置画笔宽度为10px

        textPaint.setColor(Color.BLACK);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(25);              // 设置字体大小

    }
    public PView(Context context) {
        super(context);
    }

    public PView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);      //取出宽度的确切数值
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);      //取出宽度的测量模式

        int heightsize = MeasureSpec.getSize(heightMeasureSpec);    //取出高度的确切数值
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);    //取出高度的测量模式
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 第一种
        float maxWidth = width-100;
        float mWidth = (float) (maxWidth * progress);
        RectF rectF = new RectF(0,height/2-10,maxWidth,height/2+10);
        canvas.drawRoundRect(rectF,10,10,mPaint);
        RectF rectF1 = new RectF(0,height/2-10,mWidth,height/2+10);
        canvas.drawRoundRect(rectF1,10,10,mPaint1);


        String str = ""+Integer.valueOf((int) (progress*100))+"%";
        // 参数分别为 (文本 基线x 基线y 画笔)
        canvas.drawText(str,width-80,height/2+8,textPaint);

    }

    public double getProgress() {
        return progress;

    }

    public void setProgress(double progress) {
        if (progress>1){
            this.progress = 1;
        }else if (progress<0){
            this.progress = 0;
        }else {
            this.progress = progress;
        }
        if (progress > 0.5){
            mPaint1.setColor(max);
        }else {
            mPaint1.setColor(min);
        }
        invalidate();
    }
}
