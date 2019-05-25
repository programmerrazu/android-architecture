package com.android.example.devsummit.archdemo.di.module;

import com.android.example.devsummit.archdemo.App;
import com.android.example.devsummit.archdemo.config.DemoConfig;
import com.android.example.devsummit.archdemo.controller.FeedController;
import com.android.example.devsummit.archdemo.job.BaseJob;
import com.android.example.devsummit.archdemo.model.DemoDatabase;
import com.android.example.devsummit.archdemo.model.FeedModel;
import com.android.example.devsummit.archdemo.model.PostModel;
import com.android.example.devsummit.archdemo.model.UserModel;
import com.android.example.devsummit.archdemo.util.L;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;
import com.raizlabs.android.dbflow.config.FlowManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationManagerCompat;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

@Module
public class ApplicationModule {

    private final App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public UserModel userModel(SQLiteDatabase database) {
        return new UserModel(mApp, database);
    }

    @Provides
    @Singleton
    public PostModel postModel(SQLiteDatabase database) {
        return new PostModel(mApp, database);
    }

    @Provides
    @Singleton
    public FeedModel feedModel(SQLiteDatabase database) {
        return new FeedModel(mApp, database);
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public FeedController feedController() {
        return new FeedController(mApp.getAppComponent());
    }

    @Provides
    @Singleton
    public EventBus eventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    public SQLiteDatabase database() {
        return FlowManager.getDatabase(DemoDatabase.NAME).getWritableDatabase();
    }

    @Provides
    @Singleton
    public DemoConfig demoConfig() {
        return new DemoConfig(mApp);
    }

    @Provides
    @Singleton
    public JobManager jobManager() {
        Configuration config = new Configuration.Builder(mApp)
                .consumerKeepAlive(45).maxConsumerCount(3).minConsumerCount(1).customLogger(L.getJobLogger()).injector(new DependencyInjector() {
                    @Override
                    public void inject(Job job) {
                        if (job instanceof BaseJob) {
                            ((BaseJob) job).inject(mApp.getAppComponent());
                        }
                    }
                }).build();
        return new JobManager(mApp, config);
    }

    @Provides
    @Singleton
    public NotificationManagerCompat notificationCompat() {
        return NotificationManagerCompat.from(mApp);
    }
}