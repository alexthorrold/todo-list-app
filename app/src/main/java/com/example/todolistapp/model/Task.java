package com.example.todolistapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private long taskId;

    @ColumnInfo(name = "task_description")
    private String taskDescription;

    @ColumnInfo(name = "priority")
    private Priority priority;

    @ColumnInfo(name = "due_date")
    private Date dueDate;

    @ColumnInfo(name = "is_finished")
    private boolean isFinished;

    public Task() {
    }

    @Ignore
    public Task(String taskDescription, Priority priority, Date dueDate, boolean isFinished) {
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isFinished = isFinished;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @NonNull
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskDescription='" + taskDescription + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", isFinished=" + isFinished +
                '}';
    }
}
