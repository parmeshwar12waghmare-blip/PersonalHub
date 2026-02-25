package com.example.studentperformance.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.studentperformance.data.AppDatabase;
import com.example.studentperformance.data.TaskEntity;
import java.util.List;
import java.util.concurrent.Executors;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Executors.newSingleThreadExecutor().execute(() -> {
                List<TaskEntity> futureTasks = AppDatabase.getDatabase(context).appDao().getFutureTasks(System.currentTimeMillis());
                for (TaskEntity task : futureTasks) {
                    if (task.isHasNotification) {
                        scheduleAlarm(context, task);
                    }
                }
            });
        }
    }

    private void scheduleAlarm(Context context, TaskEntity task) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("task_id", task.id);
        
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 
                task.id, 
                intent, 
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        if (alarmManager != null) {
            alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    task.dueDate,
                    pendingIntent
            );
        }
    }
}