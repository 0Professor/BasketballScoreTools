package com.example.basketballscoretools.ui.notifications.Login;

import static android.view.View.LAYER_TYPE_SOFTWARE;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ButtonLightUtil extends View {
    private Paint lightPaint;
    private int centerX, centerY;
    /** 发光范围 */
    private int radioRadius = 70;

    public ButtonLightUtil(Context context) {
        super(context);
        init();
    }

    public ButtonLightUtil(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        lightPaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        lightPaint.setColor(Color.parseColor("#46A3FF"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        centerX = getLeft() + getMeasuredWidth()/2;
        centerY = getTop() + getMeasuredHeight()/2;
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setBlurType(int blurType) {
        switch (blurType) {
            case 0:
                lightPaint.setMaskFilter(new BlurMaskFilter(radioRadius, BlurMaskFilter.Blur.OUTER));
                break;
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(centerX, centerY, 150, lightPaint);
    }
}
