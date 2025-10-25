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

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    EditText txt_email, txt_pass;
    TextView tv_reg;
    Button btnLogin;
    FirebaseAuth mAuth;

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
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = txt_email.getText().toString();
                password = txt_pass.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),
                                            "Authentication Successful",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),
                                            HomeActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(),
                                            "Authentication failed",
                                            Toast.LENGTH_LONG).show();
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
        startActivity(new Intent(this, Register.class));
    }
}