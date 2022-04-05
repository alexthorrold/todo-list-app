package com.example.todolistapp.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.R;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.util.Utils;
import com.google.android.material.chip.Chip;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

//    private OnContactClickListener onContactClickListener;
    private final List<Task> taskList;
    private final OnTodoClickListener todoClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnTodoClickListener onTodoClickListener;
        public AppCompatRadioButton radioButton;
        public AppCompatTextView taskDescription;
        public Chip dateChip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            radioButton = itemView.findViewById(R.id.radio_button_done);
            taskDescription = itemView.findViewById(R.id.text_description);
            dateChip = itemView.findViewById(R.id.chip_schedule);

            this.onTodoClickListener = todoClickListener;

            itemView.setOnClickListener(this);
            radioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            Task currentTask = taskList.get(getAdapterPosition());


            if (id == R.id.todo_constraint) {
                onTodoClickListener.onTodoClick(getAdapterPosition(), currentTask);
            }
            else {
                onTodoClickListener.onTodoRadioButtonClick(getAdapterPosition(), currentTask);
            }
        }
    }

    public interface OnContactClickListener {

        public void onContactClick(int position);
    }

    public RecyclerViewAdapter(List<Task> taskList, OnTodoClickListener onTodoClickListener) {
        this.taskList = taskList;
        this.todoClickListener = onTodoClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        String formattedDate = Utils.formatDate(task.getDueDate());

        ColorStateList colorStateList = new ColorStateList(new int[][]{
                new int[] {-android.R.attr.state_enabled},
                new int[] {android.R.attr.state_enabled}
        },
                new int[]{
                        Color.LTGRAY,
                        Utils.priorityColor(task)
                });

        holder.taskDescription.setText(task.getTaskDescription());
        holder.radioButton.setButtonTintList(colorStateList);
        holder.dateChip.setText(formattedDate);
        holder.dateChip.setTextColor(Utils.priorityColor(task));
        holder.dateChip.setChipIconTint(colorStateList);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
