package com.example.todolistapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolistapp.model.Priority;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.model.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

public class TaskEditor extends AppCompatActivity {

    public static final String ENTER_TASK_DETAILS = "Enter Task Details";
    private EditText taskDescription;
    private RadioGroup priorityRadioGroup;
    private CalendarView calendarView;
    private Priority currentPriority = Priority.MEDIUM;
    private Task task = null;
    private Date dueDate;
    private Calendar calendar = Calendar.getInstance();
    private ImageButton saveButton;
    private FloatingActionButton fab;

    TaskViewModel taskViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_editor);

        taskDescription = findViewById(R.id.text_edit_task);
        priorityRadioGroup = findViewById(R.id.radio_group);
        calendarView = findViewById(R.id.calendar_view);
        saveButton = findViewById(R.id.button_add_task);
        fab = findViewById(R.id.fab_delete);

        dueDate = Calendar.getInstance().getTime();

        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(
                TaskEditor.this.getApplication())
                .create(TaskViewModel.class);

        if (getIntent().hasExtra(MainActivity.TASK_ID)) {
            int taskId = (int) getIntent().getLongExtra(MainActivity.TASK_ID, 0);

            taskViewModel.get(taskId).observe(this, task -> {
                if (task != null) {
                    taskDescription.setText(task.getTaskDescription());
                    currentPriority = task.getPriority();
                    dueDate = task.getDueDate();
                    this.task = task;

                    switch (currentPriority) {
                        case HIGH:
                            priorityRadioGroup.check(R.id.radio_button_high_priority);
                            break;
                        case MEDIUM:
                            priorityRadioGroup.check(R.id.radio_button_medium_priority);
                            break;
                        case LOW:
                            priorityRadioGroup.check(R.id.radio_button_low_priority);
                            break;
                    }
                }
            });
        }

        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(
                TaskEditor.this.getApplication())
                .create(TaskViewModel.class);

        priorityRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.radio_button_high_priority:
                    Log.d("TAG", "HIGH");
                    currentPriority = Priority.HIGH;
                    break;
                case R.id.radio_button_medium_priority:
                    Log.d("TAG", "MEDIUM");
                    currentPriority = Priority.MEDIUM;
                    break;
                case R.id.radio_button_low_priority:
                    Log.d("TAG", "LOW");
                    currentPriority = Priority.LOW;
                    break;
            }
        });

        saveButton.setOnClickListener(view -> {
            String taskText = taskDescription.getText().toString();

            if (!TextUtils.isEmpty(taskText)) {
                if (task == null) {
                    task = new Task(taskText, currentPriority, dueDate, false);

                    TaskViewModel.insert(task);
                }
                else {
                    task.setTaskDescription(taskText);
                    task.setPriority(currentPriority);
                    task.setDueDate(dueDate);

                    TaskViewModel.update(task);
                }

                finish();
            }
            else {
                Snackbar.make(taskDescription, ENTER_TASK_DETAILS, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        calendarView.setOnDateChangeListener((calendarView, year, month, day) -> {
            //Starts from 1
            month++;

            calendar.clear();
            calendar.set(year, month, day);
            dueDate = calendar.getTime();
        });

        fab.setOnClickListener(view -> {
            if (task != null) {
                TaskViewModel.delete(task);
            }

            finish();
        });
    }
}
