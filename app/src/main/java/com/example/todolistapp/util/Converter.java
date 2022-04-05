package com.example.todolistapp.util;

import androidx.room.TypeConverter;

import com.example.todolistapp.model.Priority;

import java.util.Date;

public class Converter {

    @TypeConverter
    public static Date timestampToDate(Long value) {
        if (value != null) {
            return new Date(value);
        }
        else {
            return null;
        }
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        if (date != null) {
            return date.getTime();
        }
        else {
            return null;
        }
    }

    @TypeConverter
    public static String fromPriority(Priority priority) {
        if (priority != null) {
            return priority.name();
        }
        else {
            return null;
        }
    }

    @TypeConverter
    public static Priority toPriority(String priority) {
        if (priority != null) {
            return Priority.valueOf(priority);
        }
        else {
            return null;
        }
    }
}
