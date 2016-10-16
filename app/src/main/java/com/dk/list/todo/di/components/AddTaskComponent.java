package com.dk.list.todo.di.components;

import com.dk.list.todo.addtask.AddTaskActivity;
import com.dk.list.todo.di.ActivityScope;
import com.dk.list.todo.di.ApplicationComponent;
import com.dk.list.todo.di.modules.AddTaskModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = AddTaskModule.class)
public interface AddTaskComponent {
    void inject(AddTaskActivity activity);
}
