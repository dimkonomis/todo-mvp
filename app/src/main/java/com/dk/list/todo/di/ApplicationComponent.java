package com.dk.list.todo.di;

import com.dk.list.todo.data.RealmController;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    RealmController realmController();
}
