package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends AppCompatActivity {
    EditText txt_email, txt_pass;
    TextView tv_reg;
    Button btnLogin;
    FirebaseAuth mAuth;
    FirebaseFirestore db;  // Added for Firestore queries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_email = findViewById(R.id.txt_email);
        txt_pass = findViewById(R.id.txt_pass);
        tv_reg = findViewById(R.id.tv_reg);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();  // Initialize Firestore
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_email.getText().toString().trim();
                String password = txt_pass.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Fetch user role from Firestore after successful auth
                                    String userId = mAuth.getCurrentUser().getUid();
                                    db.collection("users").document(userId).get()
                                            .addOnSuccessListener(documentSnapshot -> {
                                                String role = documentSnapshot.getString("role");
                                                Intent intent;

                                                // Role-based redirection
                                                if ("Admin".equals(role)) {
                                                    intent = new Intent(getApplicationContext(), AdminActivity.class);
                                                } else if ("Staff".equals(role)) {
                                                    intent = new Intent(getApplicationContext(), StaffActivity.class);
                                                } else {
                                                    // Default to Customer/HomeActivity
                                                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                                                }

                                                Toast.makeText(getApplicationContext(), "Authentication Successful", Toast.LENGTH_LONG).show();
                                                startActivity(intent);
                                                finish();  // Prevent back navigation to login
                                            })
                                            .addOnFailureListener(e -> {
                                                // Handle Firestore error (e.g., network issue)
                                                Toast.makeText(getApplicationContext(), "Failed to load user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                // Default to HomeActivity if role fetch fails
                                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                                finish();
                                            });
                                } else {
                                    Toast.makeText(getApplicationContext(), "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }

    public void openHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void openRegisterActivity(View view) {
        startActivity(new Intent(this, register.class));
    }
}