package com.dk.list.todo.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dk.list.todo.R;
import com.dk.list.todo.data.RealmController;
import com.dk.list.todo.data.Task;
import com.dk.list.todo.edittask.EditTaskActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListTaskAdapter extends RecyclerView.Adapter<ListTaskAdapter.ViewHolder> {

    private Context context;
    private List<Task> tasks;
    private RealmController realmController;
    private PopupMenu popupMenu;

    public ListTaskAdapter(Context context, List<Task> tasks, RealmController realmController) {
        this.context = context;
        this.tasks   = tasks;
        this.realmController = realmController;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.check_task_completed.setChecked(tasks.get(position).getmCompleted());
        viewHolder.text_task_title.setText(tasks.get(position).getmTitle());
        viewHolder.text_task_description.setText(tasks.get(position).getmDescription());
    }

    public void showPopup(final int position, View view, final long id) {

        popupMenu = new PopupMenu(context, view, Gravity.RIGHT);
        popupMenu.inflate(R.menu.task_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.task_delete:
                        realmController.removeTask(id);
                        notifyItemRemoved(position);
                        return true;
                    case R.id.task_edit:
                        Intent intent = new Intent(context, EditTaskActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("taskID", id);
                        ((Activity) context).startActivityForResult(intent,5);
                        return true;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.check_task_completed)
        CheckBox check_task_completed;
        @BindView(R.id.text_task_title)
        TextView text_task_title;
        @BindView(R.id.text_task_description)
        TextView text_task_description;
        @BindView(R.id.image_task_more)
        ImageView image_task_more;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            check_task_completed.setOnClickListener(this);
            image_task_more.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final int postPosition = this.getAdapterPosition();
            Task task = tasks.get(postPosition);
            switch (view.getId()) {
                case R.id.check_task_completed:
                    if(task.getmCompleted()) {
                        realmController.setTaskCompleted(task.getmId(), false);
                    } else {
                        realmController.setTaskCompleted(task.getmId(), true);
                    }
                    break;
                case R.id.image_task_more:
                    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                    showPopup(postPosition, view, task.getmId());
                    break;
            }
        }

    }
}

