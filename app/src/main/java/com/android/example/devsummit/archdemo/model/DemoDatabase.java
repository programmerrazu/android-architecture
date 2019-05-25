package com.android.example.devsummit.archdemo.model;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = DemoDatabase.NAME, version = 1)
public class DemoDatabase {
    public static final String NAME = "demo";
}