package com.dk.list.todo.edittask;

public interface EditTaskPresenter {

    public void getTaskDetails(long id);

    public void validateForm(long id, String title, String description, boolean completed);

    public void UpdateTask(long id, String title, String description, boolean completed);
}
