package com.example.studentperformance.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.example.studentperformance.R;
import com.example.studentperformance.data.TaskEntity;
import com.example.studentperformance.ui.DashboardActivity;

public class NotificationBuilder {

    public static Notification buildTaskReminder(Context context, TaskEntity task) {
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 
                task.id, 
                intent, 
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        return new NotificationCompat.Builder(context, NotificationLauncher.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification) // Ensure this exists
                .setContentTitle("Task Reminder: " + task.title)
                .setContentText(task.description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
    }
}