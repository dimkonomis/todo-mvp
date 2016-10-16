package com.dk.list.todo.list;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dk.list.todo.Application;
import com.dk.list.todo.R;
import com.dk.list.todo.addtask.AddTaskActivity;
import com.dk.list.todo.data.RealmController;
import com.dk.list.todo.data.Task;
import com.dk.list.todo.di.components.DaggerListTasksComponent;
import com.dk.list.todo.di.modules.ListTasksModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListTaskActivity extends AppCompatActivity implements ListTasksView {

    @BindView(R.id.fab_add_task)
    FloatingActionButton fab_add_task;
    @BindView(R.id.tasks_list)
    RecyclerView tasks_list;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    ListTaskPresenter listTaskPresenter;

    @Inject
    RealmController realmController;

    public static final int REQUEST_ADD_TASK = 1;
    public static final int REQUEST_EDIT_TASK = 5;
    private LinearLayoutManager layoutManager;
    private ListTaskAdapter listTaskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        DaggerListTasksComponent.builder()
                .applicationComponent(((Application) getApplication()).getComponent())
                .listTasksModule(new ListTasksModule(this))
                .build().inject(this);

        listTaskPresenter.loadDatas();
        initViews();
    }

    public void initViews() {
        setSupportActionBar(toolbar);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(500);
        itemAnimator.setChangeDuration(500);

        tasks_list.setItemAnimator(itemAnimator);
        tasks_list.setLayoutManager(layoutManager);

    }

    @OnClick(R.id.fab_add_task)
    public void submit(View view) {
        Intent intent = new Intent(ListTaskActivity.this, AddTaskActivity.class);
        startActivityForResult(intent, REQUEST_ADD_TASK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_ADD_TASK:
                if(resultCode == Activity.RESULT_OK) {
                    if(listTaskAdapter != null) {
                        layoutManager.scrollToPositionWithOffset(0, 0);
                        listTaskAdapter.notifyItemInserted(0);
                    } else {
                        listTaskPresenter.loadDatas();
                    }
                }
                break;
            case REQUEST_EDIT_TASK:
                if(resultCode == Activity.RESULT_OK) {
                    if(listTaskAdapter != null) {
                        listTaskAdapter.notifyItemChanged(data.getIntExtra("position", 0));
                    }
                }
                break;
        }
    }

    @Override
    public void showProgress() {
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }

    @Override
    public void setItems(List<Task> tasks) {
        listTaskAdapter = new ListTaskAdapter(ListTaskActivity.this, tasks, realmController);
        tasks_list.setAdapter(listTaskAdapter);
    }

    @Override
    public void showEmptyMessage() {
        findViewById(R.id.emptyPanel).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyMessage() {
        findViewById(R.id.emptyPanel).setVisibility(View.GONE);
    }

}
