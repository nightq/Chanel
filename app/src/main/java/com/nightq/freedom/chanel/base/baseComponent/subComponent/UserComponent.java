package com.nightq.freedom.chanel.base.baseComponent.subComponent;

import com.nightq.freedom.chanel.base.baseModule.UserModule;
import com.nightq.freedom.chanel.base.scope.UserScope;
import com.nightq.freedom.chanel.mvp.home.components.MainActivityComponent;
import com.nightq.freedom.chanel.mvp.home.modules.MainModule;

import dagger.Subcomponent;

/**
 * Created by Nightq on 16/2/3.
 */
@UserScope
@Subcomponent (modules = {UserModule.class})
public interface UserComponent  {

    MainActivityComponent plus (MainModule mainModule);

}
