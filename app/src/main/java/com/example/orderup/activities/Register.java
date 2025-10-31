package com.example.orderup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orderup.R;
import com.example.orderup.menu_object.User;
import com.example.orderup.menu_object.Role;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {
    EditText txtName, txtEmail, txtPass;
    Button btnReg;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    CollectionReference usersRef;

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

        txtName = findViewById(R.id.txt_r_name);
        txtEmail = findViewById(R.id.txt_r_email);
        txtPass = findViewById(R.id.txt_r_pass);

        btnReg = findViewById(R.id.btn_reg);
        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        usersRef = db.collection("users");

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, email, password;
                name = txtName.getText().toString();
                email = txtEmail.getText().toString();
                password = txtPass.getText().toString();

                // Basic validation
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Register User Authentication and Object
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    String userID = task.getResult().getUser().getUid();

                                    User user = new User(name, Role.Customer, 0);
                                    usersRef.document(userID).set(user)
                                            .addOnSuccessListener(documentReference -> Toast.makeText(Register.this, "User added!", Toast.LENGTH_SHORT).show())
                                            .addOnFailureListener(e -> Toast.makeText(Register.this, "Error adding user", Toast.LENGTH_SHORT).show());

                                    Toast.makeText(Register.this, "User register successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Register.this, "Unable to register user ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    public void openHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void openLogin(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}