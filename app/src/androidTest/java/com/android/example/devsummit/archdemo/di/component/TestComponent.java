package com.android.example.devsummit.archdemo.di.component;

import com.android.example.devsummit.archdemo.di.module.TestApiServiceModule;
import com.android.example.devsummit.archdemo.job.feed.FetchFeedJobTest;
import com.android.example.devsummit.archdemo.model.FeedModelTest;
import com.android.example.devsummit.archdemo.model.PostModelTest;
import com.android.example.devsummit.archdemo.di.module.ApplicationModule;
import com.android.example.devsummit.archdemo.di.module.TestApplicationModule;
import com.android.example.devsummit.archdemo.event.LoggingBus;
import com.android.example.devsummit.archdemo.model.UserModelTest;

import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestApplicationModule.class, ApplicationModule.class, TestApiServiceModule.class})
public interface TestComponent extends AppComponent {

    void inject(PostModelTest postModelTest);

    LoggingBus loggingBus();

    SQLiteDatabase database();

    void inject(FeedModelTest feedModelTest);

    void inject(UserModelTest userModelTest);

    void inject(FetchFeedJobTest fetchFeedJobTest);
}