package com.example.todolistapp.util;

import android.graphics.Color;

import com.example.todolistapp.model.Priority;
import com.example.todolistapp.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat =
                (SimpleDateFormat) SimpleDateFormat.getDateInstance();

        simpleDateFormat.applyPattern("EEE, MMM d");

        return simpleDateFormat.format(date);
    }

    public static int priorityColor(Task task) {

        int color;

        if (task.getPriority() == Priority.HIGH) {
            color = Color.rgb(201, 21, 23);
        }
        else if (task.getPriority() == Priority.MEDIUM) {
            color = Color.rgb(255, 179, 0);
        }
        else {
            color = Color.rgb(51, 181, 229);
        }

        return color;
    }
}
