package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnManageMenus = findViewById(R.id.btn_manage_menus);
        Button btnManageUsers = findViewById(R.id.btn_manage_users);
        Button btnLogout = findViewById(R.id.btn_logout);

        btnManageMenus.setOnClickListener(v -> {
            // TODO: Navigate to a menu management screen (e.g., add/edit menus)
            Toast.makeText(this, "Manage Menus - Feature coming soon!", Toast.LENGTH_SHORT).show();
        });

        btnManageUsers.setOnClickListener(v -> {
            // TODO: Navigate to a user management screen (e.g., view/edit user roles)
            Toast.makeText(this, "Manage Users - Feature coming soon!", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {
            // Logout and return to Login
            startActivity(new Intent(this, Login.class));
            finish();
        });
    }
}