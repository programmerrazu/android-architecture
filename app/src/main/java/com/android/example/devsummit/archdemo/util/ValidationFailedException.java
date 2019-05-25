package com.android.example.devsummit.archdemo.util;

public class ValidationFailedException extends RuntimeException {

    public ValidationFailedException() {
    }

    public ValidationFailedException(String detailMessage) {
        super(detailMessage);
    }
}