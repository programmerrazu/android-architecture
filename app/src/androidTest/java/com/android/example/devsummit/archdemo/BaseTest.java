package com.android.example.devsummit.archdemo;

import com.android.example.devsummit.archdemo.di.component.TestComponent;
import com.android.example.devsummit.archdemo.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import android.support.test.InstrumentationRegistry;
import android.test.AndroidTestCase;

public class BaseTest extends AndroidTestCase {

    private TestComponent mTestComponent;

    protected TestComponent getTestComponent() {
        return mTestComponent;
    }

    @Before
    public final void setupBaseTest() throws Exception {
        super.setUp();
        App app = (App) InstrumentationRegistry.getTargetContext().getApplicationContext();
        mTestComponent = TestUtil.prepare(app);
    }

    @Test
    public void setupCheck() throws Exception {
        assertNotNull(mTestComponent);
    }
}