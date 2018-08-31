package com.example.liveyourlife.mobilenavigator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mlogo;
    private EditText mEmail, mPassword;
    private Button mLogin;
    private Button mSignup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, landingpage.class);
            startActivity(intent);
            finish();
        }

        mlogo = (ImageView) findViewById(R.id.applogo);
        mLogin = (Button) findViewById(R.id.loginbutton);
        mSignup = (Button) findViewById(R.id.signupbutton);

        mEmail = (EditText) findViewById(R.id.signup_emailText);
        mPassword = (EditText) findViewById(R.id.signup_passwordText);

        mLogin.setOnClickListener(this);
        mSignup.setOnClickListener(this);

    }

    public void loginUser(){
        String m_email = mEmail.getText().toString().trim();
        String m_password = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(m_email)){
            Toast.makeText(this, "Email field empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(m_password)){
            Toast.makeText(this, "Email field empty", Toast.LENGTH_SHORT).show();
            return;
        }
        auth(m_email, m_password);
    }

    private void auth(String email, String pass){

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), landingpage.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "An error occured:" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginbutton:
                loginUser();
                break;
            case R.id.signupbutton:
                Intent intent = new Intent(this, signup.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
    }
}
