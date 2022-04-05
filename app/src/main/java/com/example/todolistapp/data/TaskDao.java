package com.example.todolistapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistapp.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("SELECT * FROM task_table " +
            "WHERE task_table.is_finished == 0 " +
            "ORDER BY " +
            "task_table.priority == 'HIGH' DESC, " +
            "task_table.priority == 'MEDIUM' DESC, " +
            "task_table.priority == 'LOW' DESC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task_table WHERE task_table.task_id == :id")
    LiveData<Task> getTask(long id);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();
}
