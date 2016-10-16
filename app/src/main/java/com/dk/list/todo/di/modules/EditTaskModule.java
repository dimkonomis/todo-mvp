package com.dk.list.todo.di.modules;

import com.dk.list.todo.data.RealmController;
import com.dk.list.todo.di.ActivityScope;
import com.dk.list.todo.edittask.EditTaskPresenter;
import com.dk.list.todo.edittask.EditTaskPresenterImpl;
import com.dk.list.todo.edittask.EditTaskView;

import dagger.Module;
import dagger.Provides;

@Module
public class EditTaskModule {

    private EditTaskView editTaskView;

    public EditTaskModule(EditTaskView editTaskView) {
        this.editTaskView = editTaskView;
    }

    @Provides
    @ActivityScope
    public EditTaskView provideEditTaskView() {
        return editTaskView;
    }

    @Provides
    @ActivityScope
    public EditTaskPresenter providePresenter(EditTaskView editTaskView, RealmController realmController) {
        return new EditTaskPresenterImpl(editTaskView, realmController);
    }
}
