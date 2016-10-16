package com.dk.list.todo.di.modules;

import com.dk.list.todo.addtask.AddTaskPresenter;
import com.dk.list.todo.addtask.AddTaskPresenterImpl;
import com.dk.list.todo.addtask.AddTaskView;
import com.dk.list.todo.data.RealmController;
import com.dk.list.todo.di.ActivityScope;;

import dagger.Module;
import dagger.Provides;

@Module
public class AddTaskModule {

    private AddTaskView addTaskView;

    public AddTaskModule(AddTaskView addTaskView) {
        this.addTaskView = addTaskView;
    }

    @Provides
    @ActivityScope
    public AddTaskView provideAddTaskView() {
        return addTaskView;
    }

    @Provides
    @ActivityScope
    public AddTaskPresenter providePresenter(AddTaskView addTaskView, RealmController realmController) {
        return new AddTaskPresenterImpl(addTaskView, realmController);
    }

}
