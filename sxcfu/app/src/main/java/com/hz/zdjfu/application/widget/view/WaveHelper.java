package com.hz.zdjfu.application.widget.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;


/**
 * 波浪工具
 */
public class WaveHelper {
    private WaveViewNew mWaveView;
   private Float waterLevel = 0.0F;
    private AnimatorSet mAnimatorSet;
    List<Animator> animators = new ArrayList<>();
    public WaveHelper(WaveViewNew waveView) {
        mWaveView = waveView;
        initAnimation();
    }

    public void start() {

        ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(mWaveView, "waterLevelRatio", 0f, this.waterLevel);
        waterLevelAnim.setDuration(4000);
        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        if(!animators.contains(waterLevelAnim)){
            animators.add(waterLevelAnim);
            mAnimatorSet.playTogether(animators);
            mWaveView.setShowWave(true);
            if (mAnimatorSet != null) {
                mAnimatorSet.start();
            }
        }
    }

    public void setWaterLevel(Float waterLevel) {
        this.waterLevel = waterLevel;
    }

    private void initAnimation() {

        // horizontal animation.
        // wave waves infinitely.
        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(mWaveView, "waveShiftRatio", 0f, 1f);
        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
        waveShiftAnim.setDuration(1000);
        waveShiftAnim.setInterpolator(new LinearInterpolator());
        animators.add(waveShiftAnim);

        // vertical animation.
       // DecelerateInterpolator 在动画开始的地方快然后慢
       // LinearInterpolator   以常量速率改变
        // 水位从0到设置的水位高度
       /* ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(
                mWaveView, "waterLevelRatio", 0f, waterLevel);
        waterLevelAnim.setDuration(5000);
        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        animators.add(waterLevelAnim);*/

        // amplitude animation.
        // wave grows big then grows small, repeatedly
 /*       ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(
                mWaveView, "amplitudeRatio", 0.0001f, 0.05f);
        amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
        amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
        amplitudeAnim.setDuration(5000);
        amplitudeAnim.setInterpolator(new LinearInterpolator());
        animators.add(amplitudeAnim);*/

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(animators);

    }

    public void cancel() {
        if (mAnimatorSet != null) {
          //  mAnimatorSet.cancel();
            mAnimatorSet.end();
        }
    }
}
