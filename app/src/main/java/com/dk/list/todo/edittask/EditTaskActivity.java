package com.dk.list.todo.edittask;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.dk.list.todo.Application;
import com.dk.list.todo.R;
import com.dk.list.todo.di.components.DaggerEditTaskComponent;
import com.dk.list.todo.di.modules.EditTaskModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTaskActivity extends AppCompatActivity implements EditTaskView{

    @BindView(R.id.edit_title)
    EditText edit_title;
    @BindView(R.id.edit_description)
    EditText edit_description;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nestedToolbar)
    Toolbar nestedToolbar;

    @Inject
    EditTaskPresenter editTaskPresenter;

    private long taskID;
    private boolean taskStatus;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

        DaggerEditTaskComponent.builder()
                .applicationComponent(((Application) getApplication()).getComponent())
                .editTaskModule(new EditTaskModule(this))
                .build().inject(this);

        taskID = getIntent().getLongExtra("taskID", 0);

        editTaskPresenter.getTaskDetails(taskID);
        initViews();
    }

    public void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        nestedToolbar.inflateMenu(R.menu.add_menu);
        nestedToolbar.setTitle(getString(R.string.task_edit));
        nestedToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                editTaskPresenter.validateForm(taskID, edit_title.getText().toString().trim(), edit_description.getText().toString().trim(), taskStatus);
                return true;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void taskDetails(String title, String description, boolean status) {
        edit_title.setText(title);
        edit_description.setText(description);
        taskStatus = status;
    }

    @Override
    public void taskEdited() {
        Intent intent = new Intent();
        intent.putExtra("position", getIntent().getIntExtra("position", 0));
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void validationFormError() {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.coordinatorLayout), getString(R.string.error_form), Snackbar.LENGTH_LONG);
        snackbar.show();
    }


}
