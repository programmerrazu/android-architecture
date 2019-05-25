package com.android.example.devsummit.archdemo.view.activity;

import com.android.example.devsummit.archdemo.App;
import com.android.example.devsummit.archdemo.di.component.ActivityComponent;
import com.android.example.devsummit.archdemo.di.component.DaggerActivityComponent;
import com.android.example.devsummit.archdemo.util.LifecycleListener;
import com.android.example.devsummit.archdemo.util.LifecycleProvider;
import com.path.android.jobqueue.TagConstraint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class BaseActivity extends AppCompatActivity implements LifecycleProvider {

    private ActivityComponent mComponent;
    private String mSessionId;
    private final CopyOnWriteArrayList<LifecycleListener> mLifecycleListeners = new CopyOnWriteArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerActivityComponent.builder().appComponent(getApp().getAppComponent()).build();
    }

    protected App getApp() {
        return (App) getApplicationContext();
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSessionId = UUID.randomUUID().toString();
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (LifecycleListener callback : mLifecycleListeners) {
            callback.onProviderStopped();
        }
        getComponent().jobManager().cancelJobsInBackground(null, TagConstraint.ALL, mSessionId);
        mLifecycleListeners.clear();
    }

    public String getSessionId() {
        return mSessionId;
    }


    public void addLifecycleListener(LifecycleListener listener) {
        mLifecycleListeners.add(listener);
    }

    public void removeLifecycleListener(LifecycleListener listener) {
        mLifecycleListeners.remove(listener);
    }
}