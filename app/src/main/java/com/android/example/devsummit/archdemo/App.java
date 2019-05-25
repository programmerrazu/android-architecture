package com.android.example.devsummit.archdemo;

import com.android.example.devsummit.archdemo.di.component.AppComponent;
import com.android.example.devsummit.archdemo.di.component.DaggerAppComponent;
import com.android.example.devsummit.archdemo.di.module.ApplicationModule;
import com.raizlabs.android.dbflow.config.FlowManager;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        mAppComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }
}