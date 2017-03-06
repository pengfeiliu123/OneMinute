package com.lpf.oneminute.base;

import android.view.View;

/**
 * Created by liupengfei on 17/3/5 09:03.
 */

public interface BaseView<T> {

    // set presenter
    void setPresenter(T presenter);

    //init View
    void initViews(View view);

}
