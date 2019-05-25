package com.android.example.devsummit.archdemo.model;

import com.android.example.devsummit.archdemo.App;
import com.android.example.devsummit.archdemo.util.ValidationUtil;
import com.android.example.devsummit.archdemo.vo.User;
import com.android.example.devsummit.archdemo.vo.User$Table;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModel extends BaseModel {

    public UserModel(App app, SQLiteDatabase database) {
        super(app, database);
    }

    public void save(User user) {
        user.validate();
        user.save();
    }

    public void saveAll(final List<User> users) {
        ValidationUtil.pruneInvalid(users);
        if (users.isEmpty()) {
            return;
        }
        TransactionManager.transact(mSQLiteDatabase, new Runnable() {
            @Override
            public void run() {
                for (User user : users) {
                    user.save();
                }
            }
        });
    }

    public User load(long id) {
        return new Select().from(User.class)
                .where(Condition.column(User$Table.MID).eq(id))
                .querySingle();
    }

    public Map<Long, User> loadUsersAsMap(List<Long> userIds) {
        final HashMap<Long, User> result = new HashMap<>();
        if (userIds.isEmpty()) {
            return result;
        }
        long first = userIds.get(0);
        List<Long> rest = userIds.subList(1, userIds.size());
        FlowCursorList<User> userFlowCursorList = new Select().from(User.class).
                where(Condition.column(User$Table.MID).in(first, rest.toArray())).queryCursorList();
        try {
            final int size = userFlowCursorList.getCount();
            for (int i = 0; i < size; i++) {
                User user = userFlowCursorList.getItem(i);
                result.put(user.getId(), user);
            }
        } finally {
            userFlowCursorList.close();
        }

        return result;
    }
}