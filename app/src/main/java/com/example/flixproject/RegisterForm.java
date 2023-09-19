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

public class RegisterForm extends AppCompatActivity {

    TextInputEditText emailRegTxt, passRegTxt;
    Button signUp;
    TextView signIn;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        emailRegTxt = findViewById(R.id.emailSignup);
        passRegTxt = findViewById(R.id.passSignup);
        signUp = findViewById(R.id.reg_signupBtn);
        signIn=findViewById(R.id.reg_signinBtn);

        // set onclicklistner for navigation
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterForm.this, LoginForm.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(emailRegTxt.getText());
                password = String.valueOf(passRegTxt.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterForm.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterForm.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterForm.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterForm.this, LoginForm.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    finish();
                                }
                                else{
                                    Toast.makeText(RegisterForm.this, "Invalid type : Email or Password ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}