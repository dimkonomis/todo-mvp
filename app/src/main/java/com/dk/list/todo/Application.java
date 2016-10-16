package com.dk.list.todo;

import android.support.v7.app.AppCompatDelegate;

import com.dk.list.todo.di.ApplicationComponent;
import com.dk.list.todo.di.ApplicationModule;
import com.dk.list.todo.di.DaggerApplicationComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Application extends android.app.Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {

        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
