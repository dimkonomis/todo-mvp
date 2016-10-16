package com.dk.list.todo.di.modules;

import com.dk.list.todo.data.RealmController;
import com.dk.list.todo.di.ActivityScope;
import com.dk.list.todo.list.ListTaskPresenter;
import com.dk.list.todo.list.ListTaskPresenterImpl;
import com.dk.list.todo.list.ListTasksView;

import dagger.Module;
import dagger.Provides;

@Module
public class ListTasksModule {

    private ListTasksView listTasksView;

    public ListTasksModule(ListTasksView listTasksView) {
        this.listTasksView = listTasksView;
    }

    @Provides
    @ActivityScope
    public ListTasksView provideListTasksView() {
        return listTasksView;
    }

    @Provides
    @ActivityScope
    public ListTaskPresenter providePresenter(ListTasksView listTasksView, RealmController realmController) {
        return new ListTaskPresenterImpl(listTasksView, realmController);
    }

}
