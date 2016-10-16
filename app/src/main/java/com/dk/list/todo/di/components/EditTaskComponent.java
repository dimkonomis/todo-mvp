package com.dk.list.todo.di.components;

import com.dk.list.todo.di.ActivityScope;
import com.dk.list.todo.di.ApplicationComponent;
import com.dk.list.todo.di.modules.EditTaskModule;
import com.dk.list.todo.edittask.EditTaskActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = EditTaskModule.class)
public interface EditTaskComponent {
    void inject(EditTaskActivity activity);
}
