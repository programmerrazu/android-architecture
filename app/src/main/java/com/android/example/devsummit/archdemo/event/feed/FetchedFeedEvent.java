package com.android.example.devsummit.archdemo.event.feed;

import com.android.example.devsummit.archdemo.vo.Post;

import android.support.annotation.Nullable;

public class FetchedFeedEvent {

    private final boolean mSuccess;
    @Nullable
    private final Long mUserId;
    private final Post mOldest;

    public FetchedFeedEvent(boolean success, @Nullable Long userId, @Nullable Post oldest) {
        mSuccess = success;
        mUserId = userId;
        mOldest = oldest;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    @Nullable
    public Long getUserId() {
        return mUserId;
    }

    @Nullable
    public Post getOldest() {
        return mOldest;
    }
}