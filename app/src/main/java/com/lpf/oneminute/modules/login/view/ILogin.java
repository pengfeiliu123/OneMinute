package com.lpf.oneminute.modules.login.view;

/**
 * Created by liupengfei on 17/2/24 17:17.
 */

public interface ILogin {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
