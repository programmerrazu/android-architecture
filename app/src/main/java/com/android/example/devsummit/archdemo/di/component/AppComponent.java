package com.android.example.devsummit.archdemo.di.component;

import com.android.example.devsummit.archdemo.api.ApiModule;
import com.android.example.devsummit.archdemo.api.ApiService;
import com.android.example.devsummit.archdemo.config.DemoConfig;
import com.android.example.devsummit.archdemo.controller.FeedController;
import com.android.example.devsummit.archdemo.di.module.ApplicationModule;
import com.android.example.devsummit.archdemo.job.feed.FetchFeedJob;
import com.android.example.devsummit.archdemo.job.post.SaveNewPostJob;
import com.android.example.devsummit.archdemo.model.FeedModel;
import com.android.example.devsummit.archdemo.model.PostModel;
import com.android.example.devsummit.archdemo.model.UserModel;
import com.path.android.jobqueue.JobManager;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

import javax.inject.Singleton;

import dagger.Component;
import de.greenrobot.event.EventBus;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {

    JobManager jobManager();

    UserModel userModel();

    PostModel postModel();

    EventBus eventBus();

    ApiService apiService();

    FeedModel feedModel();

    FeedController feedController();

    Context appContext();

    DemoConfig demoConfig();

    NotificationManagerCompat notificationManagerCompat();

    void inject(FeedController feedController);

    void inject(FeedModel feedModel);

    void inject(FetchFeedJob fetchFeedJob);

    void inject(SaveNewPostJob saveNewPostJob);
}