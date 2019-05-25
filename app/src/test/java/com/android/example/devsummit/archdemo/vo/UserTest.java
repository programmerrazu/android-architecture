package com.android.example.devsummit.archdemo.vo;

import com.android.example.devsummit.archdemo.util.ValidationFailedException;

import org.junit.Test;

public class UserTest {

    @Test
    public void valid() {
        User user = new User();
        user.setId(1);
        user.setName("abc");
        user.validate();
    }

    @Test(expected = ValidationFailedException.class)
    public void invalidId() {
        User user = new User();
        user.setId(-1);
        user.setName("bla");
        user.validate();
    }

    @Test(expected = ValidationFailedException.class)
    public void invalidName() {
        User user = new User();
        user.setId(1);
        user.setName(null);
        user.validate();
    }
}