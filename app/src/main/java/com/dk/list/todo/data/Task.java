package com.dk.list.todo.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject{

    @PrimaryKey
    private long mId;
    private String mTitle;
    private String mDescription;
    private boolean mCompleted;

    public Task() {

    }

    public Task(long mId, String mTitle, String mDescription, boolean mCompleted) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mCompleted = mCompleted;
    }

    public void setmId(long id) {
        this.mId = id;
    }

    public void setmTitle(String title) {
        this.mTitle = title;
    }

    public void setmDescription(String description) {
        this.mDescription = description;
    }

    public void setmCompleted(boolean completed) {
        this.mCompleted = completed;
    }

    public long getmId() { return mId; }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public boolean getmCompleted() {
        return mCompleted;
    }

}
