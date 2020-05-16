package com.crowforce.terminal.appcentertest;

import android.app.Application;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;

public class App extends Application {
    Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        Distribute.disableAutomaticCheckForUpdate();
        AppCenter.start(application, "dc7b4681-011b-4ebd-a9c8-502a9c3b572a", Distribute.class);
        AppCenter.start(application, "dc7b4681-011b-4ebd-a9c8-502a9c3b572a", Analytics.class, Crashes.class);
    }
}
