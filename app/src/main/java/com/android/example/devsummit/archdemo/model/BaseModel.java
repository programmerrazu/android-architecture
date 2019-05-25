package com.android.example.devsummit.archdemo.model;

import com.android.example.devsummit.archdemo.App;
import com.android.example.devsummit.archdemo.di.component.AppComponent;

import android.database.sqlite.SQLiteDatabase;

public class BaseModel {

    protected final SQLiteDatabase mSQLiteDatabase;
    protected final AppComponent mComponent;

    public BaseModel(App app, SQLiteDatabase database) {
        mComponent = app.getAppComponent();
        mSQLiteDatabase = database;
    }
}