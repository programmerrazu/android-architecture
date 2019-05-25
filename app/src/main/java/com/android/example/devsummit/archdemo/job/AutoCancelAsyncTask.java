package com.android.example.devsummit.archdemo.job;

import com.android.example.devsummit.archdemo.util.LifecycleProvider;
import com.android.example.devsummit.archdemo.util.LifecycleListener;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public abstract class AutoCancelAsyncTask<Params, Result> extends AsyncTask<Params, Void, Result> implements LifecycleListener {

    private final WeakReference<LifecycleProvider> mLifecycleProviderRef;

    public AutoCancelAsyncTask(LifecycleProvider lifecycleProvider) {
        lifecycleProvider.addLifecycleListener(this);
        mLifecycleProviderRef = new WeakReference<>(lifecycleProvider);
    }

    @Override
    public void onProviderStopped() {
        cancel(false);
    }

    private void stopListening() {
        LifecycleProvider lifecycleProvider = mLifecycleProviderRef.get();
        if (lifecycleProvider != null) {
            lifecycleProvider.removeLifecycleListener(this);
        }
    }

    @Override
    protected final void onCancelled(Result result) {
        super.onCancelled(result);
        stopListening();
        onCancelled();
    }

    @SafeVarargs
    @Override
    protected final Result doInBackground(Params... params) {
        if (isCancelled()) {
            stopListening();
            return null;
        }
        try {
            return onDoInBackground(params);
        } catch (Throwable t) {
            stopListening();
            throw t;
        }
    }

    @Override
    protected final void onPostExecute(Result result) {
        stopListening();
        if (isCancelled()) {
            return;
        }
        onResult(result);
    }

    protected abstract void onResult(Result result);

    @SuppressWarnings("unchecked")
    protected abstract Result onDoInBackground(Params... params);

    @SuppressWarnings("unused")
    protected void onCancel() {
    }
}