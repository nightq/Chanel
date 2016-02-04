package com.nightq.freedom.chanel.mvp.login.modules;

import android.content.SharedPreferences;

import com.firebase.client.Firebase;
import com.nightq.freedom.chanel.base.scope.ActivityScope;
import com.nightq.freedom.chanel.mvp.login.presenters.ILoginPresenter;
import com.nightq.freedom.chanel.mvp.login.presenters.LoginPresenter;
import com.nightq.freedom.chanel.mvp.login.views.LoginActivity;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Nightq on 16/2/2.
 */
@Module
public class LoginModule {

    @NonNull
    private LoginActivity loginActivity;

    public LoginModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @ActivityScope
    @Provides
    public LoginActivity provideLoginActivity() {
        return loginActivity;
    }

    @ActivityScope
    @Provides
    public ILoginPresenter provideLoginPresenter(Firebase firebase, SharedPreferences sharedPreferences) {
        return new LoginPresenter(loginActivity, firebase, sharedPreferences);
    }

}
