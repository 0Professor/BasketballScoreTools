package com.example.basketballscoretools.ui.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.basketballscoretools.R;


public class CircleCount extends View {

    private int StartColor = Color.BLACK;
    private int EndColor = Color.WHITE;

    private int BorderWidth = 10;
    private int TextSize;
    private int TextColor;

    private int StepMax;
    private int CurrentStep;

    private Paint StartPaint;
    private Paint EndPaint;
    private Paint TextPaint;

    public CircleCount(Context context) {
        this(context,null);
    }
    public CircleCount(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public CircleCount(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /*获取参数*/
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CircleCount);

        StartColor = array.getColor(R.styleable.CircleCount_start_color,StartColor);
        EndColor = array.getColor(R.styleable.CircleCount_end_color,EndColor);
        BorderWidth = (int) array.getDimension(R.styleable.CircleCount_progress_rate_width,BorderWidth);
        TextColor = array.getColor(R.styleable.CircleCount_stepTextColor,TextColor);
        TextSize = array.getDimensionPixelSize(R.styleable.CircleCount_stepTextSize,TextSize);
        /*回收*/
        array.recycle();

        StartPaint = new Paint();
        StartPaint.setAntiAlias(true);
        StartPaint.setStrokeWidth(BorderWidth);
        StartPaint.setColor(StartColor);
        StartPaint.setStyle(Paint.Style.STROKE);

        TextPaint = new Paint();
        TextPaint.setColor(Color.BLACK);
        TextPaint.setTextSize(TextSize);
        TextPaint.setAntiAlias(true);

        EndPaint = new Paint();
        EndPaint.setAntiAlias(true);
        EndPaint.setStrokeWidth(BorderWidth);
        EndPaint.setColor(EndColor);
        EndPaint.setStyle(Paint.Style.STROKE);
        EndPaint.setStrokeCap(Paint.Cap.ROUND);
    }
    

    /**/
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(BorderWidth/2,BorderWidth/2,
                getWidth()-BorderWidth/2,getHeight()-BorderWidth/2);
        canvas.drawArc(rectF,90,360,false,StartPaint);

        if(StepMax==0)
            return;

        float sweep = (float) CurrentStep / StepMax;

        canvas.drawArc(rectF,90,sweep*360,false,EndPaint);

        String stepText = CurrentStep + "%";
        Rect rect = new Rect();

        TextPaint.getTextBounds(stepText,0,stepText.length(),rect);
        int dx = getWidth()/2 - rect.width()/2;

        Paint.FontMetricsInt fontMetricsInt = TextPaint.getFontMetricsInt();

        int dy = (fontMetricsInt.bottom - fontMetricsInt.top)/2 - fontMetricsInt.bottom;
        int baseLine = getHeight()/2 + dy;
        canvas.drawText(stepText,dx,baseLine,TextPaint);
    }

    public synchronized void setStepMax(int stepMax) {
        this.StepMax = stepMax;
    }

    public synchronized void setCurrentStep(int currentStep) {
        this.CurrentStep = currentStep;
        invalidate();
    }
}
