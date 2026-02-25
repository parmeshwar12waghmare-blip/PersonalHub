package com.example.studentperformance.receivers;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.studentperformance.data.AppDatabase;
import com.example.studentperformance.data.TaskEntity;
import com.example.studentperformance.notifications.NotificationBuilder;
import com.example.studentperformance.notifications.NotificationLauncher;
import java.util.concurrent.Executors;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int taskId = intent.getIntExtra("task_id", -1);
        if (taskId == -1) return;

        Executors.newSingleThreadExecutor().execute(() -> {
            TaskEntity task = AppDatabase.getDatabase(context).appDao().getTaskById(taskId);
            if (task != null) {
                Notification notification = NotificationBuilder.buildTaskReminder(context, task);
                NotificationLauncher.launch(context, task.id, notification);
            }
        });
    }
}