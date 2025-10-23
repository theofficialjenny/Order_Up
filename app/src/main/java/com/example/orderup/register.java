package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    EditText txtEmail, txtPass;
    Spinner spinnerRole;  // Added for role selection
    Button btnReg;
    FirebaseAuth mAuth;
    FirebaseFirestore db;  // Added for Firestore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtEmail = findViewById(R.id.txt_r_email);
        txtPass = findViewById(R.id.txt_r_pass);
        spinnerRole = findViewById(R.id.spinner_role);  // Initialize Spinner (ensure it's in your XML)
        btnReg = findViewById(R.id.btn_reg);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();  // Initialize Firestore

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPass.getText().toString().trim();
                String role = spinnerRole.getSelectedItem().toString();

                // Basic validation
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(register.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Save user role to Firestore after successful registration
                                    String userId = mAuth.getCurrentUser().getUid();
                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("role", role);

                                    db.collection("users").document(userId).set(userData)
                                            .addOnSuccessListener(aVoid -> {
                                                Toast.makeText(register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                                startActivity(intent);
                                                finish();  // Prevent back navigation
                                            })
                                            .addOnFailureListener(e -> {
                                                Toast.makeText(register.this, "Registration successful, but failed to save role: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                // Still proceed to HomeActivity as auth succeeded
                                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                                finish();
                                            });
                                } else {
                                    Toast.makeText(register.this, "Unable to register user: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void openHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}