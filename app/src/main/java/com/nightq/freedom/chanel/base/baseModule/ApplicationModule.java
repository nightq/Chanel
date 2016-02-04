package com.nightq.freedom.chanel.base.baseModule;

import com.nightq.freedom.chanel.application.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nightq on 16/2/2.
 */
@Module
public class ApplicationModule {

    private MainApplication mainApplication;

    public ApplicationModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Provides
    @Singleton
    public MainApplication provideApplication(){
        return mainApplication;
    }

}
