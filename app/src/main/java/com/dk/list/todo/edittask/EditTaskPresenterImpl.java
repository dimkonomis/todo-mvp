package com.dk.list.todo.edittask;

import com.dk.list.todo.data.RealmController;
import com.dk.list.todo.data.Task;

import javax.inject.Inject;

public class EditTaskPresenterImpl implements EditTaskPresenter {

    private EditTaskView editTaskView;
    RealmController realmController;

    @Inject
    public EditTaskPresenterImpl(EditTaskView editTaskView, RealmController realmController) {
        this.editTaskView = editTaskView;
        this.realmController = realmController;
    }

    @Override
    public void getTaskDetails(long id) {
        Task task = realmController.getTask(id);
        editTaskView.taskDetails(task.getmTitle(), task.getmDescription(), task.getmCompleted());
    }

    @Override
    public void validateForm(long id, String title, String description, boolean completed) {
        if(title.length() > 0 && description.length() > 0) {
            UpdateTask(id, title, description, completed);
        } else {
            editTaskView.validationFormError();
        }
    }

    @Override
    public void UpdateTask(long id, String title, String description, boolean completed) {
        realmController.updateTask(id, title, description, completed);
        editTaskView.taskEdited();
    }
}
