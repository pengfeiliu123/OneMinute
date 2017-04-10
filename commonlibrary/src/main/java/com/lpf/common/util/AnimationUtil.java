package com.lpf.common.util;

import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import static android.R.attr.fromXDelta;

/**
 * Created by liupengfei on 17/3/10 14:00.
 */

public class AnimationUtil {

    // horizontal swing
    public static TranslateAnimation setSwingH(float fromXDelta ,float toXDelta){
        TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta,toXDelta,0,0);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        translateAnimation.setDuration(50);
        translateAnimation.setRepeatCount(5);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        return translateAnimation;
    }
}
