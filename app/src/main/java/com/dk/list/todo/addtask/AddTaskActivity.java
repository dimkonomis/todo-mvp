package com.dk.list.todo.addtask;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.dk.list.todo.Application;
import com.dk.list.todo.R;
import com.dk.list.todo.di.components.DaggerAddTaskComponent;
import com.dk.list.todo.di.modules.AddTaskModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskActivity extends AppCompatActivity implements AddTaskView{

    @BindView(R.id.edit_title)
    EditText edit_title;
    @BindView(R.id.edit_description)
    EditText edit_description;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nestedToolbar)
    Toolbar nestedToolbar;

    @Inject
    AddTaskPresenter addTaskPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

        DaggerAddTaskComponent.builder()
                .applicationComponent(((Application) getApplication()).getComponent())
                .addTaskModule(new AddTaskModule(this))
                .build().inject(this);

        initViews();
    }

    public void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        nestedToolbar.inflateMenu(R.menu.add_menu);
        nestedToolbar.setTitle(getString(R.string.task_add));
        nestedToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                addTaskPresenter.validateForm(edit_title.getText().toString().trim(), edit_description.getText().toString().trim());
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
    public void taskAdded() {
        Intent intent = new Intent();
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
