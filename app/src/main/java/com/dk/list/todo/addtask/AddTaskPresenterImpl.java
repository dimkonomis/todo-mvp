package com.dk.list.todo.addtask;

import com.dk.list.todo.data.RealmController;

import javax.inject.Inject;

public class AddTaskPresenterImpl implements AddTaskPresenter {

    private AddTaskView addTaskView;
    RealmController realmController;

    @Inject
    public AddTaskPresenterImpl(AddTaskView addTaskView, RealmController realmController) {
        this.addTaskView = addTaskView;
        this.realmController = realmController;
    }

    @Override
    public void validateForm(String title, String description) {
        if(title.length() > 0 && description.length() > 0) {
            submitForm(title, description);
        } else {
            addTaskView.validationFormError();
        }
    }

    @Override
    public void submitForm(String title, String description) {
        long uuid = System.currentTimeMillis();
        realmController.setTask(uuid, title, description);
        addTaskView.taskAdded();
    }
}
