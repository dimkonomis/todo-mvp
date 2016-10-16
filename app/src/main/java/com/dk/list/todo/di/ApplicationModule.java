package com.dk.list.todo.di;

import com.dk.list.todo.Application;
import com.dk.list.todo.data.RealmController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    RealmController provideRealmController() {
        return new RealmController();
    }
}
