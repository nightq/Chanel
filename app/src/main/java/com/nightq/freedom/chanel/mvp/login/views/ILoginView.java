package com.nightq.freedom.chanel.mvp.login.views;

import com.nightq.freedom.chanel.baseModels.UserModel;

/**
 * Created by Nightq on 16/2/3.
 */
public interface ILoginView {

    /**
     * 登陆成功
     * @param userModel
     */
     void loginSuccess(UserModel userModel);

    /**
     * 显示隐藏进度
     * @param show
     */
    void showProgress(boolean show);

    void showAccountError(String error);

    void showPasswordError(String error);
}
