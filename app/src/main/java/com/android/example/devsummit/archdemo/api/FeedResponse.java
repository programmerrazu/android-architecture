package com.android.example.devsummit.archdemo.api;

import com.android.example.devsummit.archdemo.vo.Post;
import com.android.example.devsummit.archdemo.vo.User;

import java.util.List;

public class FeedResponse {

    private List<Post> mPosts;
    private List<User> mUsers;

    public List<Post> getPosts() {
        return mPosts;
    }

    public void setPosts(List<Post> posts) {
        this.mPosts = posts;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        this.mUsers = users;
    }
}