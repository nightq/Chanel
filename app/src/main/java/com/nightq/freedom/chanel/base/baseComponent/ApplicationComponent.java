package com.nightq.freedom.chanel.base.baseComponent;

import com.nightq.freedom.chanel.base.baseComponent.subComponent.UserComponent;
import com.nightq.freedom.chanel.base.baseModule.AppServiceModule;
import com.nightq.freedom.chanel.base.baseModule.ApplicationModule;
import com.nightq.freedom.chanel.base.baseModule.ServerApiModule;
import com.nightq.freedom.chanel.base.baseModule.UserModule;
import com.nightq.freedom.chanel.mvp.login.components.LoginActivityComponent;
import com.nightq.freedom.chanel.mvp.login.modules.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nightq on 16/2/2.
 */
@Singleton
@Component(modules = {ApplicationModule.class, AppServiceModule.class, ServerApiModule.class})
public interface ApplicationComponent {
    UserComponent plus(UserModule userModule);
    LoginActivityComponent plus(LoginModule loginModule);
}
