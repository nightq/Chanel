package com.nightq.freedom.chanel.base.utils;

import android.util.Log;

import com.nightq.freedom.chanel.BuildConfig;

/**
 * Created by Nightq on 16/2/4.
 */
public class LogUtil {

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

}
