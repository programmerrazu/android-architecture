package com.android.example.devsummit.archdemo.vo;

import com.android.example.devsummit.archdemo.util.ValidationFailedException;

import org.junit.Test;

import java.lang.System;

public class PostTest {

    @Test
    public void valid() {
        Post post = new Post();
        post.setUserId(1);
        post.setClientId("dsadsa");
        post.setText("abc");
        post.setCreated(System.currentTimeMillis());
        post.validate();
    }

    @Test(expected = ValidationFailedException.class)
    public void invalidUserId() {
        Post post = new Post();
        post.setUserId(-1);
        post.setClientId("dsadsa");
        post.setText("abc");
        post.setCreated(System.currentTimeMillis());
        post.validate();
    }

    @Test(expected = ValidationFailedException.class)
    public void emptyCustomId() {
        Post post = new Post();
        post.setUserId(1);
        post.setClientId(null);
        post.setText("abc");
        post.setCreated(System.currentTimeMillis());
        post.validate();
    }

    @Test(expected = ValidationFailedException.class)
    public void emptyText() {
        Post post = new Post();
        post.setUserId(1);
        post.setClientId("dsadsa");
        post.setText(null);
        post.setCreated(System.currentTimeMillis());
        post.validate();
    }
}