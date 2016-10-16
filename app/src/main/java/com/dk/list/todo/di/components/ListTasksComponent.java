package com.dk.list.todo.di.components;

import com.dk.list.todo.di.ActivityScope;
import com.dk.list.todo.di.ApplicationComponent;
import com.dk.list.todo.di.modules.ListTasksModule;
import com.dk.list.todo.list.ListTaskActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ListTasksModule.class)
public interface ListTasksComponent {
    void inject(ListTaskActivity activity);
}
