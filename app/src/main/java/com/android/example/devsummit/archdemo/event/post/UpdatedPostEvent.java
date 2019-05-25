package com.android.example.devsummit.archdemo.event.post;

import com.android.example.devsummit.archdemo.vo.Post;

public class UpdatedPostEvent {

    private final Post mPost;

    public UpdatedPostEvent(Post post) {
        mPost = post;
    }

    public Post getPost() {
        return mPost;
    }
}