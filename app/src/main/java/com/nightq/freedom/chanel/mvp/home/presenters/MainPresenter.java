package com.nightq.freedom.chanel.mvp.home.presenters;

import android.content.SharedPreferences;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.nightq.freedom.chanel.base.utils.LogUtil;
import com.nightq.freedom.chanel.baseModels.UserModel;
import com.nightq.freedom.chanel.mvp.home.views.IMainView;
import com.nightq.freedom.chanel.mvp.login.views.ILoginView;

import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * Created by Nightq on 16/2/2.
 */
@AllArgsConstructor
public class MainPresenter implements IMainPresenter {

    private IMainView iMainView;
    private Firebase firebase;
    private SharedPreferences sharedPreferences;

    @Override
    public void logout() {
        firebase.unauth();
        iMainView.showLogout();
    }

}
