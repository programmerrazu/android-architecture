package com.android.example.devsummit.archdemo.vo;

public class FeedItem {

    private final Post mPost;
    private final User mUser;

    public FeedItem(Post post, User user) {
        mPost = post;
        mUser = user;
    }

    public Post getPost() {
        return mPost;
    }

    public User getUser() {
        return mUser;
    }
}