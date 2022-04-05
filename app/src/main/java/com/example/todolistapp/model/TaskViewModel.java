package com.example.todolistapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolistapp.data.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    public static TaskRepository repository;
    public final LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        repository = new TaskRepository(application);
        allTasks = repository.getAllData();
    }

    public LiveData<Task> get(int id) {
        return repository.getTask(id);
    }

    public LiveData<List<Task>> getAllTasks() {
        return repository.getAllData();
    }

    public static void insert(Task task) {
        repository.insert(task);
    }

    public static void update(Task task) {
        repository.update(task);
    }

    public static void delete(Task task) {
        repository.delete(task);
    }

    public static void deleteAll() {
        repository.deleteAll();
    }
}
