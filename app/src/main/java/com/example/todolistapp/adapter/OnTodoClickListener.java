package com.example.todolistapp.adapter;

import com.example.todolistapp.model.Task;

public interface OnTodoClickListener {

    void onTodoClick(int adapterPosition, Task task);
    void onTodoRadioButtonClick(int adapterPosition, Task task);
}
