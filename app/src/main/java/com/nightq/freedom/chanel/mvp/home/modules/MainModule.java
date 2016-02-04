package com.nightq.freedom.chanel.mvp.home.modules;

import android.content.SharedPreferences;

import com.firebase.client.Firebase;
import com.nightq.freedom.chanel.base.scope.ActivityScope;
import com.nightq.freedom.chanel.mvp.home.presenters.IMainPresenter;
import com.nightq.freedom.chanel.mvp.home.presenters.MainPresenter;
import com.nightq.freedom.chanel.mvp.home.views.MainActivity;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Nightq on 16/2/2.
 */
@Module
public class MainModule {

    @NonNull
    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @ActivityScope
    @Provides
    public MainActivity provideMainActivity() {
        return mainActivity;
    }

    @ActivityScope
    @Provides
    public IMainPresenter provideMainPresenter(Firebase firebase, SharedPreferences sharedPreferences) {
        return new MainPresenter(mainActivity, firebase, sharedPreferences);
    }

}
