package com.android.example.devsummit.archdemo.api;

import com.android.example.devsummit.archdemo.vo.Post;
import com.android.example.devsummit.archdemo.vo.User;

public class NewPostResponse {

    private Post mPost;
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Post getPost() {
        return mPost;
    }

    public void setPost(Post post) {
        mPost = post;
    }
}