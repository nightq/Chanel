package com.nightq.freedom.chanel.base.baseModule;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nightq on 16/2/2.
 */
@Module
public class AppServiceModule {

    private final Application application;

    public AppServiceModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SharedPreferences providePreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Firebase provideFirebase() {
        return new Firebase("https://chanel.firebaseio.com/");
    }

}
