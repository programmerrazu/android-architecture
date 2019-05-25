package com.android.example.devsummit.archdemo.event;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

@SuppressWarnings("unchecked")
public class LoggingBus extends EventBus {

    private final List<Object> mEvents = new ArrayList<>();

    @Override
    public void post(Object event) {
        mEvents.add(event);
        super.post(event);
    }

    public void clear() {
        mEvents.clear();
    }

    public <T> T findEvent(Class<T> eventClass) {
        for (Object event : mEvents) {
            if (eventClass.isInstance(event)) {
                return (T) event;
            }
        }
        return null;
    }

    public <T> List<T> findEvents(Class<T> eventClass) {
        List<T> matching = new ArrayList<>();
        for (Object event : mEvents) {
            if (eventClass.isAssignableFrom(event.getClass())) {
                matching.add((T) event);
            }
        }
        return matching;
    }
}