package com.dk.list.todo.addtask;

public interface AddTaskPresenter {

    public void validateForm(String title, String description);

    public void submitForm(String title, String description);
}
