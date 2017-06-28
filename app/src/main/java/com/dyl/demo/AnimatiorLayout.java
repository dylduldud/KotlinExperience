package com.dyl.demo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

/**
 * Created by dengyulin on 2017/6/27.
 */

public class AnimatiorLayout extends LinearLayout {
    public AnimatiorLayout(Context context) {
        super(context);
    }

    public AnimatiorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public void show(){
        ValueAnimator animator=new ValueAnimator().ofInt(0,getMeasuredWidth());
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setTranslationX((Float) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    public void showParallax(){
        for(int i=0;i<getChildCount();i++){
            final int p=i;
            ValueAnimator animator=new ValueAnimator().ofFloat(0,getMeasuredWidth());
            animator.setDuration(500);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    getChildAt(p).setTranslationX((Float) animation.getAnimatedValue());
                }
            });
            animator.setStartDelay(100*p);
            animator.start();
        }
    }
}
