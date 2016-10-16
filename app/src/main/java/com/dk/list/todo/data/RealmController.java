package com.dk.list.todo.data;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmController {

    private final Realm realm;

    public RealmController() {
        realm = Realm.getDefaultInstance();
    }

    /**
     * Get all tasks
     */
    public RealmResults<Task> getTasks() {
        return realm.where(Task.class).findAllSorted("mId", Sort.DESCENDING);
    }

    /**
     * Get single task
     */
    public Task getTask(long id) {
        return realm.where(Task.class).equalTo("mId", id).findFirst();
    }

    /**
     * Save a new task
     */
    public void setTask(long id, String title, String description ) {
        realm.beginTransaction();
        Task task = realm.createObject(Task.class);
        task.setmId(id);
        task.setmTitle(title);
        task.setmDescription(description);
        task.setmCompleted(false);
        realm.commitTransaction();
    }

    /**
     * Set an existing task as completed or not
     */
    public void setTaskCompleted(long id, boolean completed) {
        Task task = realm.where(Task.class)
                .equalTo("mId", id).findFirst();
        realm.beginTransaction();
        task.setmCompleted(completed);
        realm.commitTransaction();
    }

    /**
     * Update an existing task
     */
    public void updateTask(long id, String title, String description, boolean completed ) {
        Task task = realm.where(Task.class)
                .equalTo("mId", id).findFirst();
        realm.beginTransaction();
        task.setmTitle(title);
        task.setmDescription(description);
        task.setmCompleted(completed);
        realm.commitTransaction();
    }

    /**
     * Remove an existing task
     */
    public void removeTask(long id) {
        Task task = realm.where(Task.class)
                .equalTo("mId", id).findFirst();
        realm.beginTransaction();
        task.deleteFromRealm();
        realm.commitTransaction();
    }

}
