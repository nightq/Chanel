package com.nightq.freedom.chanel.mvp.login.presenters;

import android.content.SharedPreferences;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.gson.Gson;
import com.nightq.freedom.chanel.base.utils.LogUtil;
import com.nightq.freedom.chanel.baseModels.UserModel;
import com.nightq.freedom.chanel.mvp.login.views.ILoginView;

import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * Created by Nightq on 16/2/2.
 */
@AllArgsConstructor
public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;
    private Firebase firebase;
    private SharedPreferences sharedPreferences;

    /**
     * 初始化检测，如果已经登陆就直接跳转
     */
    @Override
    public boolean initCheckAuth() {
        firebase.addAuthStateListener(authData -> {
            LogUtil.e("nightq", "auth data = " + authData);
            if (authData != null) {
                // user is logged in
            } else {
                // user is not logged in
            }
        });
        AuthData authData = firebase.getAuth();
        if (authData != null) {
            // user authenticated
            iLoginView.showProgress(true);
            iLoginView.loginSuccess(new UserModel(authData));
            return true;
        }
        return false;
    }

    @Override
    public void startRegister(String email, String password) {
        //  todo nightq now login
        iLoginView.showProgress(true);
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                startLogining(email, password);
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                iLoginView.showProgress(false);
                showRegisterError(firebaseError);
            }
        });
    }

    @Override
    public void startLogining(String email, String password) {
        //  todo nightq now login
        iLoginView.showProgress(true);
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                iLoginView.loginSuccess(new UserModel(authData));
                LogUtil.e("nightq",
                        "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                iLoginView.showProgress(false);
                showLoginError(firebaseError);
            }
        });
    }

    /**
     * 显示登陆错误
     */
    public void showLoginError (FirebaseError firebaseError) {
        LogUtil.e("nightq", "firebaseError  = "
                + (firebaseError != null ? new Gson().toJson(firebaseError) : null));
        if (firebaseError.getCode() == FirebaseError.INVALID_EMAIL
                || firebaseError.getCode() == FirebaseError.EMAIL_TAKEN) {
            iLoginView.showAccountError(null);
        } else if (firebaseError.getCode() == FirebaseError.INVALID_PASSWORD) {
            iLoginView.showPasswordError(null);
        }
    }

    /**
     * 显示注册错误
     */
    public void showRegisterError (FirebaseError firebaseError) {
        showLoginError(firebaseError);
    }

}
