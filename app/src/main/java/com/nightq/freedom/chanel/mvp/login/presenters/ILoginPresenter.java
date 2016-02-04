package com.nightq.freedom.chanel.mvp.login.presenters;

/**
 * Created by Nightq on 16/2/2.
 */
public interface ILoginPresenter {

    boolean initCheckAuth();
    void startLogining(String email, String password);
    void startRegister(String email, String password);

}
