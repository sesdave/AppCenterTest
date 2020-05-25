package com.crowforce.terminal.appcentertest;

import android.app.Application;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;


public class App extends Application {
    Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
       /* Distribute.disableAutomaticCheckForUpdate();
        AppCenter.start(application, "dc7b4681-011b-4ebd-a9c8-502a9c3b572a", Distribute.class);
        AppCenter.start(application, "dc7b4681-011b-4ebd-a9c8-502a9c3b572a", Analytics.class, Crashes.class);

        */
        new AppUpdater(this)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("https://github.com/sesdave/AppCenterTest/master/app/update.xml")
                .start();

    }
}
