package com.android.example.devsummit.archdemo.model;

import com.android.example.devsummit.archdemo.BaseTest;
import com.android.example.devsummit.archdemo.util.TestUtil;
import com.android.example.devsummit.archdemo.util.ValidationFailedException;
import com.android.example.devsummit.archdemo.vo.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.runner.AndroidJUnit4;

import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserModelTest extends BaseTest {

    @Inject
    UserModel mUserModel;

    @Before
    public void setup() {
        getTestComponent().inject(this);
    }

    @Test
    public void save() {
        User user = TestUtil.createDummyUser();
        mUserModel.save(user);
        User loaded = mUserModel.load(user.getId());
        assertThat(loaded, notNullValue());
        assertThat(loaded.getId(), is(user.getId()));
        assertThat(loaded.getName(), is(user.getName()));
    }

    @Test(expected = ValidationFailedException.class)
    public void saveInvalid() {
        User user = TestUtil.createDummyUser();
        user.setId(-1);
        mUserModel.save(user);
    }

    @Test
    public void saveAll() {
        User u1 = TestUtil.createDummyUser();
        User u2 = TestUtil.createDummyUser();
        mUserModel.saveAll(Arrays.asList(u1, u2));
        assertThat(mUserModel.load(u1.getId()), notNullValue());
        assertThat(mUserModel.load(u2.getId()), notNullValue());
    }

    @Test
    public void loadAsMap() {
        User u1 = TestUtil.createDummyUser();
        User u2 = TestUtil.createDummyUser();
        mUserModel.saveAll(Arrays.asList(u1, u2));
        Map<Long, User> loaded = mUserModel
                .loadUsersAsMap(Arrays.asList(u1.getId(), u2.getId(), u2.getId() + 1));
        assertThat(loaded.size(), is(2));
        assertThat(loaded.get(u1.getId()), notNullValue());
        assertThat(loaded.get(u1.getId()).getId(), is(u1.getId()));
        assertThat(loaded.get(u2.getId()), notNullValue());
        assertThat(loaded.get(u2.getId()).getId(), is(u2.getId()));
    }
}