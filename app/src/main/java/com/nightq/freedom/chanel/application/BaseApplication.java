package com.nightq.freedom.chanel.application;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;
import com.nightq.freedom.chanel.BuildConfig;

import lombok.Getter;

/**
 * Created by Nightq on 16/2/2.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        if (BuildConfig.DEBUG) {
//            Dagger2Metrics.enableCapturing(this);
        }
    }

}
