package com.android.example.devsummit.archdemo.util;

public class TimestampTracker {

    private static final long NaN = Long.MAX_VALUE;
    private long mCurrent = NaN;
    private long mNext = NaN;

    public void updateNext(long timestamp) {
        if (timestamp < mNext) {
            mNext = timestamp;
        }
    }

    public boolean hasTimestamp() {
        return mCurrent != NaN;
    }

    public void updateCurrent(long timestamp) {
        if (timestamp < mCurrent) {
            mCurrent = timestamp;
        }
    }

    public void swap() {
        mCurrent = mNext;
        mNext = NaN;
    }

    public void reset() {
        mCurrent = mNext = NaN;
    }

    public long getCurrent() {
        return mCurrent;
    }
}