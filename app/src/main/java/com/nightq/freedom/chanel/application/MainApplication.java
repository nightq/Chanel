package com.nightq.freedom.chanel.application;

import android.content.Context;

import com.nightq.freedom.chanel.base.baseComponent.ApplicationComponent;
import com.nightq.freedom.chanel.base.baseComponent.DaggerApplicationComponent;
import com.nightq.freedom.chanel.base.baseModule.ApplicationModule;
import com.nightq.freedom.chanel.base.baseModule.UserModule;
import com.nightq.freedom.chanel.base.baseModule.AppServiceModule;
import com.nightq.freedom.chanel.baseModels.UserModel;
import com.nightq.freedom.chanel.base.baseComponent.subComponent.UserComponent;

import lombok.Getter;

/**
 * Created by Nightq on 16/2/2.
 */
public class MainApplication extends BaseApplication {

    @Getter private ApplicationComponent applicationComponent;
    @Getter private UserComponent userComponent;


    public static MainApplication getInstance(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
    }

    /**
     * 初始化
     */
    private void initApplicationComponent() {
        applicationComponent =
                DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .appServiceModule(new AppServiceModule(this))
                        .build();
    }

    /**
     * 创建用户
     * @param userModel
     * @return
     */
    public UserComponent createUserComponent(UserModel userModel) {
        userComponent = applicationComponent.plus(new UserModule(userModel));
        return userComponent;
    }

    public void releaseUserComponent() {
        userComponent = null;
    }



}
