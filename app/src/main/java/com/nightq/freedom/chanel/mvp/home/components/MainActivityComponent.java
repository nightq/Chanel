package com.nightq.freedom.chanel.mvp.home.components;

import com.nightq.freedom.chanel.base.scope.ActivityScope;
import com.nightq.freedom.chanel.mvp.home.modules.MainModule;
import com.nightq.freedom.chanel.mvp.home.views.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Nightq on 16/2/2.
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainActivityComponent {

    MainActivity inject(MainActivity mainActivity);

}
