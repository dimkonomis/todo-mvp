package com.dk.list.todo.edittask;

public interface EditTaskView {

    /**
     * Show task details.
     */
    void taskDetails(String title, String description, boolean completed);

    /**
     * Edit selected task.
     */
    void taskEdited();

    /**
     * Show message on form error.
     */
    void validationFormError();

}
