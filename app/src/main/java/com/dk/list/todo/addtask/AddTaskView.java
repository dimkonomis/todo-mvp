package com.dk.list.todo.addtask;

public interface AddTaskView {

    /**
     * Add new task to list.
     */
    void taskAdded();

    /**
     * Show message on form error.
     */
    void validationFormError();

}
