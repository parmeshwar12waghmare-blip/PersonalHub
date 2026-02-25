package com.example.studentperformance.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentperformance.data.AppDatabase;
import com.example.studentperformance.data.UserEntity;
import com.example.studentperformance.databinding.ActivityLoginBinding;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Pre-fill a dummy user for demonstration if none exists
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(this);
            if (db.appDao().getUser() == null) {
                db.appDao().insertUser(new UserEntity("John Doe", "test@example.com", 
                    "https://www.w3schools.com/howto/img_avatar.png", "password123"));
            }
        });

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            Executors.newSingleThreadExecutor().execute(() -> {
                UserEntity user = AppDatabase.getDatabase(this).appDao().getUser();
                runOnUiThread(() -> {
                    if (user != null && user.email.equals(email) && user.password.equals(password)) {
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });
    }
}