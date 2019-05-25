package com.android.example.devsummit.archdemo.job;

public class NetworkException extends RuntimeException {

    private final int mErrorCode;

    public NetworkException(int errorCode) {
        mErrorCode = errorCode;
    }

    public boolean shouldRetry() {
        return mErrorCode < 400 || mErrorCode > 499;
    }
}