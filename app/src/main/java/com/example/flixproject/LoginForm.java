package com.example.flixproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginForm extends AppCompatActivity {

    TextInputEditText emailLoginTxt, passLoginTxt;
    Button signIn;
    TextView signUp;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        emailLoginTxt = findViewById(R.id.emailLogin);
        passLoginTxt = findViewById(R.id.passLogin);
        signIn = findViewById(R.id.sign_signinBtn);
        signUp=findViewById(R.id.sign_signupBtn);

        // set onclicklistner for navigation
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForm.this, RegisterForm.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(emailLoginTxt.getText());
                password = String.valueOf(passLoginTxt.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginForm.this, "Type Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginForm.this, "Type Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginForm.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginForm.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    finish();
                                }
                                else
                                    Toast.makeText(LoginForm.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}