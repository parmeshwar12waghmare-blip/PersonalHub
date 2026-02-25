package com.example.studentperformance.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey
    public int id = 1;
    public String name;
    public String email;
    public String profileImage;
    public String password;

    public UserEntity(String name, String email, String profileImage, String password) {
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.password = password;
    }
}