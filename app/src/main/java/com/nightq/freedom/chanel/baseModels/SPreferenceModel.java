package com.nightq.freedom.chanel.baseModels;

import android.content.SharedPreferences;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Nightq on 16/2/3.
 */
@AllArgsConstructor
@Data public class SPreferenceModel {

    private SharedPreferences sharedPreferences;

    /**
     * 获取编辑
     * @return
     */
    public SharedPreferences.Editor edit () {
        return sharedPreferences.edit();
    }
}
