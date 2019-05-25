package com.android.example.devsummit.archdemo.di.module;

import com.android.example.devsummit.archdemo.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestApiServiceModule {

    @Provides
    @Singleton
    public ApiService apiService() {
        return mock(ApiService.class);
    }
}