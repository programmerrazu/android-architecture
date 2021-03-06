package com.android.example.devsummit.archdemo.util;

import com.path.android.jobqueue.log.CustomLogger;

import android.util.Log;

public class L {

    private static final String TAG = "[DEMO]";

    public static void d(String msg, Object... args) {
        Log.d(TAG, String.format(msg, args));
    }

    public static void e(Throwable t, String msg, Object... args) {
        Log.e(TAG, String.format(msg, args), t);
    }

    public static CustomLogger getJobLogger() {
        return instance;
    }

    private static final CustomLogger instance = new CustomLogger() {
        @Override
        public boolean isDebugEnabled() {
            return true;
        }

        @Override
        public void d(String text, Object... args) {
            L.d(text, args);
        }

        @Override
        public void e(Throwable t, String text, Object... args) {
            L.e(t, text, args);
        }

        @Override
        public void e(String text, Object... args) {
            L.d(text, args);
        }
    };
}