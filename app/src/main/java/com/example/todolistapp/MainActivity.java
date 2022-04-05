package com.example.todolistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.todolistapp.adapter.OnTodoClickListener;
import com.example.todolistapp.adapter.RecyclerViewAdapter;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.model.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements OnTodoClickListener {


    public static final String TASK_ID = "task_id";

    private TaskViewModel taskViewModel;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab_add);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(
                MainActivity.this.getApplication())
                .create(TaskViewModel.class);

        taskViewModel.getAllTasks().observe(this, tasks -> {
            for (Task task : tasks) {
                Log.d("TAG", "onCreate: " + task.getTaskDescription());
            }

            recyclerViewAdapter = new RecyclerViewAdapter(tasks, this);

            recyclerView.setAdapter(recyclerViewAdapter);
        });

//        activityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(), result -> {
//                    if (result.getResultCode())
//                }
//        );

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TaskEditor.class);
            startActivity(intent);
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void onTodoClick(int adapterPosition, Task task) {
//        Log.d("Clicked", "onContactClick: " + task.getTaskId());

        Intent intent = new Intent(MainActivity.this, TaskEditor.class);
        intent.putExtra(TASK_ID, task.getTaskId());
        startActivity(intent);
    }

    @Override
    public void onTodoRadioButtonClick(int adapterPosition, Task task) {
        task.setFinished(true);
        TaskViewModel.update(task);
        recyclerViewAdapter.notifyItemChanged(adapterPosition);
    }
}