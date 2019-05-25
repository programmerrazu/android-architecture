package com.android.example.devsummit.archdemo.view.activity;

import com.android.example.devsummit.archdemo.R;
import com.android.example.devsummit.archdemo.config.DemoConfig;
import com.android.example.devsummit.archdemo.databinding.SettingsBinding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

public class SettingsActivity extends BaseActivity {

    @Inject
    DemoConfig mDemoConfig;

    private SettingsBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.settings);
        mBinding.setConfig(mDemoConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            long newUid = Long.parseLong(mBinding.userId.getText().toString());
            mDemoConfig.setUserId(newUid);
        } catch (Throwable ignored) {
        }
        try {
            int newPostRetryCount = Integer.parseInt(mBinding.newPostRetryCount.getText().toString());
            mDemoConfig.setNewPostRetryCount(newPostRetryCount);
        } catch (Throwable ignored) {
        }
        mDemoConfig.setApiUrl(mBinding.apiUrl.getText().toString());
    }

    public static Intent intentFor(Context source) {
        return new Intent(source, SettingsActivity.class);
    }
}