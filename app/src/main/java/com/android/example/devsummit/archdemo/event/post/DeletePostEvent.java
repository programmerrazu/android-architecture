package com.android.example.devsummit.archdemo.event.post;

import com.android.example.devsummit.archdemo.vo.Post;

import android.support.annotation.Nullable;

public class DeletePostEvent {

    @Nullable
    private final Post mPost;
    private final boolean mSyncFailure;
    private final String mText;
    private boolean mNotifiedUser;

    public DeletePostEvent(boolean syncFailure, String text, @Nullable Post post) {
        mPost = post;
        mSyncFailure = syncFailure;
        mText = text;
    }

    public void markAsNotifiedUser() {
        mNotifiedUser = true;
    }

    public boolean didNotifyUser() {
        return mNotifiedUser;
    }

    @Nullable
    public Post getPost() {
        return mPost;
    }

    public boolean isSyncFailure() {
        return mSyncFailure;
    }

    public String getText() {
        return mText;
    }
}