package com.example.smartstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private EditText editemail,editpassword;
    private Button login;
    private TextView forgotPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        editemail=(EditText) findViewById(R.id.email_log);
        editpassword=(EditText) findViewById(R.id.pass_log);

        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.forogtPassword);
        forgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.register:
                startActivity(new Intent(this,Register.class));
                break;

            case R.id.login:
                userLogin();
                break;

            case R.id.forogtPassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = editemail.getText().toString().trim();
        String password = editpassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editemail.setError("Email Required!");
            editemail.requestFocus();
            return;
        }
        //if(!Pattern.EMAIL_ADDRESS.mathcer(email.matches()))
        if(password.isEmpty())
        {
            editpassword.setError("Password Required!");
            editpassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editpassword.setError("Minimum 6 char required ");
            editpassword.requestFocus();
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(MainActivity.this,Home.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "Failed to Login! Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}