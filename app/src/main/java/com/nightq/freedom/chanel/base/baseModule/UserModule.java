package com.nightq.freedom.chanel.base.baseModule;

import com.nightq.freedom.chanel.base.scope.UserScope;
import com.nightq.freedom.chanel.baseModels.UserModel;

import dagger.Module;
import dagger.Provides;

/**
 */
@Module
public class UserModule {

    private UserModel userModel;

    public UserModule(UserModel userModel) {
        this.userModel = userModel;
    }

    @Provides
    @UserScope
    UserModel provideUserModule() {
        return userModel;
    }

}
