package com.example.todolistapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todolistapp.model.Task;
import com.example.todolistapp.util.TaskRoomDatabase;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<Task> getTask(int id) {
        return taskDao.getTask(id);
    }

    public LiveData<List<Task>> getAllData() {
        return allTasks;
    }

    public void insert(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.insert(task));
    }

    public void update(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.update(task));
    }

    public void delete(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.delete(task));
    }

    public void deleteAll() {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.deleteAll());
    }
}
