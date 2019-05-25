package com.android.example.devsummit.archdemo.util;

public interface LifecycleProvider {

    void addLifecycleListener(LifecycleListener listener);

    void removeLifecycleListener(LifecycleListener listener);
}