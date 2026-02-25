package com.example.studentperformance.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class TaskEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public long dueDate;
    public String priority;
    public boolean isHasNotification;

    public TaskEntity(String title, String description, long dueDate, String priority, boolean isHasNotification) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isHasNotification = isHasNotification;
    }
}