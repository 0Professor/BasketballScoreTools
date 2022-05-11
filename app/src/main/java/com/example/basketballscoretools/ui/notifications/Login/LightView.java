package com.example.basketballscoretools.ui.notifications.Login;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.basketballscoretools.R;

public class LightView extends View {

    private Paint mPaint;

    public LightView(Context context) {
        this(context,null);

    }

    public LightView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public LightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}
