package com.dk.list.todo.list;

import com.dk.list.todo.data.Task;

import java.util.List;


public interface ListTasksView {

    /**
     * Show progress bar.
     */
    void showProgress();

    /**
     * Hide progress bar
     */
    void hideProgress();

    /**
     * Set tasks
     */
    void setItems(List<Task> tasks);

    /**
     * Show no tasks message
     */
    void showEmptyMessage();

    /**
     * Hide no tasks message
     */
    void hideEmptyMessage();

}
