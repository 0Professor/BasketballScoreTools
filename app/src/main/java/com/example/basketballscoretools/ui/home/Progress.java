package com.example.basketballscoretools.ui.home;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.example.basketballscoretools.R;

public class Progress extends DialogFragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /*背景透明*/
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        CircleCount circleCount = null;

        if(view == null){
            view = inflater.inflate(R.layout.fragment_home_progress,container);
            circleCount = view.findViewById(R.id.circle_id);
        }

        circleCount.setStepMax(100);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        final CircleCount finalCircleCount = circleCount;
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                finalCircleCount.setCurrentStep((int) currentStep);
                
                /*如果进度条到100了就关闭*/
                if (currentStep == 100) {
                    dismiss();
                }
            }
        });
        valueAnimator.start();
        return view;
    }
}



