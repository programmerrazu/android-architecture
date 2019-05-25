package com.android.example.devsummit.archdemo.event.post;

import com.android.example.devsummit.archdemo.vo.Post;

public class NewPostEvent {

    private final Post mPost;

    public NewPostEvent(Post post) {
        mPost = post;
    }

    public Post getPost() {
        return mPost;
    }
}