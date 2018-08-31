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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener{

    private ImageView mLogo;
    private Button mSignup;
    private EditText mEmail, mPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        mLogo = (ImageView) findViewById(R.id.signlogo);
        mSignup = (Button) findViewById(R.id.signupsubmitbutton);

        mEmail = (EditText) findViewById(R.id.signup_emailText);
        mPassword = (EditText) findViewById(R.id.signup_passwordText);

        mSignup.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signup(){
        String m_email = mEmail.getText().toString().trim();
        String m_pass = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(m_email)){
            Toast.makeText(this, "Email field empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(m_pass)){
            Toast.makeText(this, "Email field empty", Toast.LENGTH_SHORT).show();
        }
        authSignup(m_email, m_pass);
    };

    private void authSignup(String email, String pass){
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup.this, "Sign-up success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), landingpage.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signupsubmitbutton:
                signup();
                break;
        }
    }
}
