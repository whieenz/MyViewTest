package com.whieenz.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;


/**
 * Created by heziwen on 2017/10/20.
 * 作用：三步操作进度条
 */

public class ThreeStepProgressView extends View {

    private int min = Color.parseColor("#FF7F00");
    private int max = Color.parseColor("#00B2EE");
    private int width;
    private int height;
    private int stepNum;
    private List<String> stepInfo;
    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    private Paint mPaint1 = new Paint();
    Paint textPaint = new Paint();          // 创建画笔

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.parseColor("#bebebe"));       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setStrokeWidth(3f);         //设置画笔宽度为10px
        mPaint.setAntiAlias(true);         //设置抗锯齿

        mPaint1.setColor(Color.parseColor("#bebebe"));       //设置画笔颜色
        mPaint1.setStyle(Paint.Style.FILL_AND_STROKE);  //设置画笔模式为填充

        textPaint.setColor(Color.parseColor("#bebebe"));        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(25);              // 设置字体大小

    }
    public ThreeStepProgressView(Context context) {
        super(context);
    }

    public ThreeStepProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ThreeStepProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        int lineWidth = height / 8;
        int minRadius = height / 8;
        int maxRadius = height / 8;
        canvas.drawCircle(10+height/8,height/4,minRadius,mPaint);
        mPaint1.setStrokeWidth(lineWidth);
        canvas.drawLine(10+height/4,height/4,width/2-minRadius,height/4,mPaint1);
        canvas.drawCircle(width/2,height/4,minRadius,mPaint);
        canvas.drawLine(width/2+minRadius,height/4,width-10-minRadius*2,height/4,mPaint1);
        canvas.drawCircle(width-10-minRadius,height/4,minRadius,mPaint);



    }

}
