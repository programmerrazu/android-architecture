package com.android.example.devsummit.archdemo.di.module;

import com.android.example.devsummit.archdemo.event.LoggingBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

@Singleton
@Module
public class TestApplicationModule {
    @Provides
    @Singleton
    public LoggingBus loggingBus(EventBus eventBus) {
        return (LoggingBus) eventBus;
    }
}