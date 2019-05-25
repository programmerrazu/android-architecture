package com.android.example.devsummit.archdemo.api;

import com.android.example.devsummit.archdemo.config.DemoConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public ApiService apiService(DemoConfig demoConfig) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder().baseUrl(demoConfig.getApiUrl())
                .addConverterFactory(JacksonConverterFactory.create(mapper)).build().create(ApiService.class);
    }
}