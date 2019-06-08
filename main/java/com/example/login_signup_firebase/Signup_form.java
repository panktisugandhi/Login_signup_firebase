package com.example.login_signup_firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_form extends AppCompatActivity {

   private EditText et_email,et_pass,et_con_pass;
    private Button btn_regi;
   private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("SIGN-UP FORM");

        et_email =(EditText) findViewById(R.id.et_email);
        et_pass = (EditText)findViewById(R.id.et_pass);
        et_con_pass =(EditText) findViewById(R.id.et_con_pass);
        progressBar =(ProgressBar) findViewById(R.id.reg_progressBar);

        btn_regi =(Button) findViewById(R.id.btn_reg);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = et_email.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String con_pass = et_con_pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_form.this, "Pls Enter Email..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Signup_form.this, "Pls Enter password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(con_pass)){
                    Toast.makeText(Signup_form.this, "Pls Enter confirm password..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pass.length()<6){
                    Toast.makeText(Signup_form.this, "Your Password Must Be Grater Then 6 Digit!!!!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                if (pass.equals(con_pass)){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(Signup_form.this,MainActivity.class));
                                        Toast.makeText(Signup_form.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(Signup_form.this, "Authentication failed..", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });


    }
}
