package com.android.example.devsummit.archdemo.model;

import com.android.example.devsummit.archdemo.App;
import com.android.example.devsummit.archdemo.util.ValidationUtil;
import com.android.example.devsummit.archdemo.vo.Post;
import com.android.example.devsummit.archdemo.vo.Post$Table;
import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.apache.commons.lang3.StringUtils;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.util.List;

public class PostModel extends BaseModel {

    public PostModel(App app, SQLiteDatabase database) {
        super(app, database);
    }

    public List<Post> loadPostsSince(long since) {
        return new Select().from(Post.class).where(
                Condition.column(Post$Table.MCREATED).greaterThan(since)
        ).queryList();
    }

    public List<Post> loadPostsOfUser(long userId, long since) {
        return new Select().from(Post.class).where(Condition.column(Post$Table.MCREATED).greaterThan(since)).
                and(Condition.column(Post$Table.MUSERID).eq(userId)).queryList();
    }

    public Post load(long id) {
        return new Select().from(Post.class).where(Condition.column(Post$Table.MID).eq(id)).querySingle();
    }

    public synchronized void save(Post post) {
        ValidationUtil.validate(post);
        saveValid(post);
    }

    public synchronized void saveAll(final List<Post> posts) {
        ValidationUtil.pruneInvalid(posts);
        if (posts.isEmpty()) {
            return;
        }
        TransactionManager.transact(mSQLiteDatabase, new Runnable() {
            @Override
            public void run() {
                for (Post post : posts) {
                    saveValid(post);
                }
            }
        });
    }

    private void saveValid(Post post) {
        Post existing = loadByClientIdAndUserId(post.getClientId(), post.getUserId());
        if (existing == null) {
            post.save();
        } else {
            post.setId(existing.getId());
            post.update();
        }
    }

    @Nullable
    public synchronized Post loadByClientIdAndUserId(String clientId, long userId) {
        if (StringUtils.isEmpty(clientId)) {
            return null;
        }
        return new Select().from(Post.class).where(Condition.column(Post$Table.MCLIENTID).eq(clientId))
                .and(Condition.column(Post$Table.MUSERID).eq(userId)).querySingle();
    }

    public void delete(Post post) {
        post.delete();
    }
}