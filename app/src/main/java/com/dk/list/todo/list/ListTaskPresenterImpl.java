package com.dk.list.todo.list;

import com.dk.list.todo.data.RealmController;
import com.dk.list.todo.data.Task;

import javax.inject.Inject;

import io.realm.RealmResults;

public class ListTaskPresenterImpl implements ListTaskPresenter {

    private ListTasksView listTasksView;
    RealmController realmController;

    @Inject
    public ListTaskPresenterImpl(ListTasksView listTasksView, RealmController realmController) {
        this.listTasksView = listTasksView;
        this.realmController = realmController;
    }

    @Override
    public void loadDatas() {

        RealmResults<Task> tasks = realmController.getTasks();
        listTasksView.hideProgress();
        if(tasks.size() > 0) {
            listTasksView.hideEmptyMessage();
            listTasksView.setItems(tasks);
        } else {
            listTasksView.showEmptyMessage();
        }
    }

}
