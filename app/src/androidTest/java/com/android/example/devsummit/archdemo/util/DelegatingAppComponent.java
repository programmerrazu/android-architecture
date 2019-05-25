package com.android.example.devsummit.archdemo.util;

import com.android.example.devsummit.archdemo.api.ApiService;
import com.android.example.devsummit.archdemo.config.DemoConfig;
import com.android.example.devsummit.archdemo.controller.FeedController;
import com.android.example.devsummit.archdemo.di.component.AppComponent;
import com.android.example.devsummit.archdemo.job.feed.FetchFeedJob;
import com.android.example.devsummit.archdemo.job.post.SaveNewPostJob;
import com.android.example.devsummit.archdemo.model.FeedModel;
import com.android.example.devsummit.archdemo.model.PostModel;
import com.android.example.devsummit.archdemo.model.UserModel;
import com.path.android.jobqueue.JobManager;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

import de.greenrobot.event.EventBus;

public class DelegatingAppComponent implements AppComponent {

    private final AppComponent mDelegate;

    public DelegatingAppComponent(AppComponent delegate) {
        mDelegate = delegate;
    }

    @Override
    public JobManager jobManager() {
        return mDelegate.jobManager();
    }

    @Override
    public UserModel userModel() {
        return mDelegate.userModel();
    }

    @Override
    public PostModel postModel() {
        return mDelegate.postModel();
    }

    @Override
    public EventBus eventBus() {
        return mDelegate.eventBus();
    }

    @Override
    public ApiService apiService() {
        return mDelegate.apiService();
    }

    @Override
    public FeedModel feedModel() {
        return mDelegate.feedModel();
    }

    @Override
    public FeedController feedController() {
        return mDelegate.feedController();
    }

    @Override
    public Context appContext() {
        return mDelegate.appContext();
    }

    @Override
    public DemoConfig demoConfig() {
        return mDelegate.demoConfig();
    }

    @Override
    public NotificationManagerCompat notificationManagerCompat() {
        return mDelegate.notificationManagerCompat();
    }

    @Override
    public void inject(FeedController feedController) {
        mDelegate.inject(feedController);
    }

    @Override
    public void inject(FeedModel feedModel) {
        mDelegate.inject(feedModel);
    }

    @Override
    public void inject(FetchFeedJob fetchFeedJob) {
        mDelegate.inject(fetchFeedJob);
    }

    @Override
    public void inject(SaveNewPostJob saveNewPostJob) {
        mDelegate.inject(saveNewPostJob);
    }
}