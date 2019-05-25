package com.android.example.devsummit.archdemo.controller;

import com.android.example.devsummit.archdemo.R;
import com.android.example.devsummit.archdemo.config.DemoConfig;
import com.android.example.devsummit.archdemo.di.component.AppComponent;
import com.android.example.devsummit.archdemo.event.SubscriberPriority;
import com.android.example.devsummit.archdemo.event.post.DeletePostEvent;
import com.android.example.devsummit.archdemo.job.BaseJob;
import com.android.example.devsummit.archdemo.job.feed.FetchFeedJob;
import com.android.example.devsummit.archdemo.job.post.SaveNewPostJob;
import com.android.example.devsummit.archdemo.view.activity.FeedActivity;
import com.path.android.jobqueue.JobManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.UUID;

import javax.inject.Inject;

import dagger.Lazy;
import de.greenrobot.event.EventBus;

public class FeedController {

    @Inject
    JobManager mJobManager;

    @Inject
    DemoConfig mDemoConfig;

    @Inject
    EventBus mEventBus;

    @Inject
    Context mAppContext;

    @Inject
    Lazy<NotificationManagerCompat> mNotificationManagerCompat;

    public FeedController(AppComponent appComponent) {
        appComponent.inject(this);
        mEventBus.register(this, SubscriberPriority.LOW);
    }

    public void onEventMainThread(DeletePostEvent event) {
        if (event.didNotifyUser() || !event.isSyncFailure()) {
            return;
        }
        Intent intent = FeedActivity.intentForSendPost(mAppContext, event.getText());
        PendingIntent pendingIntent = PendingIntent.getActivity(mAppContext, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mAppContext).setSmallIcon(R.drawable.ic_action_backup)
                .setContentTitle(mAppContext.getString(R.string.cannot_sync_post, event.getText())).setAutoCancel(true).setContentIntent(pendingIntent);
        mNotificationManagerCompat.get().notify(1, builder.build());
    }

    public void fetchFeedAsync(boolean fromUI, @Nullable Long userId) {
        mJobManager.addJobInBackground(new FetchFeedJob(fromUI ? BaseJob.UI_HIGH : BaseJob.BACKGROUND, userId));
    }

    public void sendPostAsync(String text) {
        mJobManager.addJobInBackground(new SaveNewPostJob(text, UUID.randomUUID().toString(), mDemoConfig.getUserId()));
    }
}