package com.example.studentperformance.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    @Query("SELECT * FROM users WHERE id = 1 LIMIT 1")
    UserEntity getUser();

    @Insert
    long insertTask(TaskEntity task);

    @Update
    void updateTask(TaskEntity task);

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    List<TaskEntity> getAllTasks();

    @Query("SELECT * FROM tasks WHERE dueDate > :currentTime")
    List<TaskEntity> getFutureTasks(long currentTime);
    
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    TaskEntity getTaskById(int taskId);
}