package com.nightq.freedom.chanel.base.widgets;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nightq.freedom.chanel.base.baseComponent.ApplicationComponent;
import com.nightq.freedom.chanel.application.MainApplication;

import butterknife.ButterKnife;

/**
 * Created by Nightq on 16/2/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(MainApplication.getInstance(this).getApplicationComponent());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected abstract void setupActivityComponent(ApplicationComponent applicationComponent);
}
