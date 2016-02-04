package com.nightq.freedom.chanel.mvp.login.components;

import com.nightq.freedom.chanel.base.scope.ActivityScope;
import com.nightq.freedom.chanel.mvp.login.modules.LoginModule;
import com.nightq.freedom.chanel.mvp.login.views.LoginActivity;

import dagger.Subcomponent;

/**
 * Created by Nightq on 16/2/2.
 */
@ActivityScope
@Subcomponent(modules = LoginModule.class)
public interface LoginActivityComponent {

    LoginActivity inject(LoginActivity loginActivity);

}
