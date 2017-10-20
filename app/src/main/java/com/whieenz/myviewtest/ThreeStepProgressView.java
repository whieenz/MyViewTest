package com.whieenz.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by heziwen on 2017/10/20.
 * 作用：三步操作进度条
 */

public class ThreeStepProgressView extends View {

    private int light = Color.parseColor("#Ff4444");
    private int grey = Color.parseColor("#bebebe");
    private int width;
    private int height;
    private int stepNum;
    private List<String> stepInfo;
    // 1.创建一个画笔
    private Paint retPaint = new Paint();
    private Paint linePaint = new Paint();
    Paint textPaint = new Paint();          // 创建画笔

    // 2.初始化画笔
    private void initPaint() {
        retPaint.setColor(light);       //设置画笔颜色
        retPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        retPaint.setStrokeWidth(4f);         //设置画笔宽度为10px
        retPaint.setAntiAlias(true);         //设置抗锯齿

        linePaint.setColor(grey);       //设置画笔颜色
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);  //设置画笔模式为填充
        linePaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint.setColor(light);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(height / 4);

    }

    public ThreeStepProgressView(Context context) {
        super(context);
    }

    public ThreeStepProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        stepNum = 1;
        stepInfo = new ArrayList<>();
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

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        if (stepNum < 1) {
            this.stepNum = 1;
        } else if (stepNum > 3) {
            this.stepNum = 3;
        } else {
            this.stepNum = stepNum;
        }

    }

    public List<String> getStepInfo() {
        return stepInfo;
    }

    public void setStepInfo(List<String> stepInfo) {
        this.stepInfo = stepInfo;
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
        initPaint();
        // 第一种
        int padding = 10;
        int firstBaseLine = height / 3;
        int textBaseLine = height * 3 / 4;
        int lineWidth = height / 8;
        int minRadius = height / 8;
        int maxRadius = height / 7;
        int firstRadius = minRadius, secondRadius = minRadius, thirdRadius = minRadius, firstStart, firstEnd, secondStart, secondEnd;
        if (stepNum == 1) {
            firstRadius = maxRadius;
        } else if (stepNum == 2) {
            secondRadius = maxRadius;
        } else if (stepNum == 3) {
            thirdRadius = maxRadius;
        }
        firstStart = padding + 2 * firstRadius;
        firstEnd = width / 2 - secondRadius;
        secondStart = width / 2 + secondRadius;
        secondEnd = width - padding - 2 * thirdRadius;
        retPaint.setStrokeWidth(firstRadius / 4);
        canvas.drawCircle(padding + height / 8, firstBaseLine, firstRadius, retPaint);
        if (stepNum > 1) {
            linePaint.setColor(light);
            retPaint.setColor(light);
        } else {
            retPaint.setColor(grey);
        }
        linePaint.setStrokeWidth(lineWidth);
        retPaint.setStrokeWidth(secondRadius / 4);
        canvas.drawLine(firstStart, firstBaseLine, firstEnd, firstBaseLine, linePaint);
        canvas.drawCircle(width / 2, firstBaseLine, secondRadius, retPaint);
        if (stepNum == 3) {
            linePaint.setColor(light);
            retPaint.setColor(light);
        } else {
            linePaint.setColor(grey);
            retPaint.setColor(grey);
        }
        retPaint.setStrokeWidth(thirdRadius / 4);
        canvas.drawLine(secondStart, firstBaseLine, secondEnd, firstBaseLine, linePaint);
        canvas.drawCircle(width - 10 - minRadius, firstBaseLine, thirdRadius, retPaint);

        if (stepInfo == null || stepInfo.size() < 3) {
            stepInfo = new ArrayList<>();
            stepInfo.add("第一步");
            stepInfo.add("第二步");
            stepInfo.add("第三步");
        }
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(stepInfo.get(0), padding, textBaseLine, textPaint);
        textPaint.setTextAlign(Paint.Align.CENTER);
        if (stepNum == 1) {
            textPaint.setColor(grey);
        } else {
            textPaint.setColor(light);
        }
        canvas.drawText(stepInfo.get(1), width / 2, textBaseLine, textPaint);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        if (stepNum == 3) {
            textPaint.setColor(light);
        } else {
            textPaint.setColor(grey);
        }
        canvas.drawText(stepInfo.get(2), width - padding, textBaseLine, textPaint);
    }


}
