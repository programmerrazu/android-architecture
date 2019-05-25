package com.android.example.devsummit.archdemo.di.component;

import com.android.example.devsummit.archdemo.view.activity.FeedActivity;
import com.android.example.devsummit.archdemo.di.scope.ActivityScope;
import com.android.example.devsummit.archdemo.view.activity.SettingsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent {

    void inject(SettingsActivity settingsActivity);

    void inject(FeedActivity feedActivity);
}