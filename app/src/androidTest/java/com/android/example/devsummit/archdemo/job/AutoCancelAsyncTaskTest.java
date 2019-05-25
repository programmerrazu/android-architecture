package com.android.example.devsummit.archdemo.job;

import com.android.example.devsummit.archdemo.util.LifecycleListener;
import com.android.example.devsummit.archdemo.util.LifecycleProvider;

import org.junit.Test;

import java.util.concurrent.Executor;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AutoCancelAsyncTaskTest {

    @Test
    public void unregisterAfterCompletion() {
        Executor mockExecutor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };
        LifecycleProvider provider = mock(LifecycleProvider.class);
        doNothing().when(provider).addLifecycleListener(any(LifecycleListener.class));
        AutoCancelAsyncTask<Void, Void> task = new AutoCancelAsyncTask<Void, Void>(provider) {
            @Override
            protected void onResult(Void aVoid) {

            }

            @Override
            protected Void onDoInBackground(Void... params) {
                return null;
            }
        };
        task.executeOnExecutor(mockExecutor);
        verify(provider).removeLifecycleListener(any(LifecycleListener.class));
    }

    @Test
    public void unregisterOnCancel() {
        LifecycleProvider provider = mock(LifecycleProvider.class);
        doNothing().when(provider).addLifecycleListener(any(LifecycleListener.class));
        AutoCancelAsyncTask<Void, Void> task = new AutoCancelAsyncTask<Void, Void>(provider) {
            @Override
            protected void onResult(Void aVoid) {

            }

            @Override
            protected Void onDoInBackground(Void... params) {
                return null;
            }
        };
        task.cancel(false);
        verify(provider).removeLifecycleListener(any(LifecycleListener.class));
    }
}