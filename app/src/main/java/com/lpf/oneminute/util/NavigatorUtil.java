package com.lpf.oneminute.util;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.lpf.oneminute.MainActivity;

/**
 * Created by liupengfei on 17/3/10 16:20.
 * Navigator for fragments
 */

public class NavigatorUtil {

    // Fragment jump

    public static void switchToFragment(Context context, Fragment toFragment) {
        ((MainActivity) context).switchToFragment(toFragment);
    }

    // change toolbar title
    public static void changeToolTitle(Context context, String title) {
        ((MainActivity) context).changeTitle(title);
    }
}
