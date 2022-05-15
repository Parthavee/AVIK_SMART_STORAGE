package com.example.smartstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView banner,registeruser;
    private EditText editFullname,editemail,editpass;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registeruser = (Button)findViewById(R.id.registerUser);
        registeruser.setOnClickListener(this);

        editFullname = (EditText) findViewById(R.id.fullname);
        editemail=(EditText) findViewById(R.id.email);
        editpass=(EditText) findViewById(R.id.password);

        progressbar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.registerUser:
                registeruser();
                break;
        }
    }

    private void registeruser() {
        String email = editemail.getText().toString().trim();
        String password = editpass.getText().toString().trim();
        String fullname = editFullname.getText().toString().trim();

        if(fullname.isEmpty())
        {
            editFullname.setError("Full name Required!");
            editFullname.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            editemail.setError("Email Required!");
            editemail.requestFocus();
            return;
        }
        //if(!Pattern.EMAIL_ADDRESS.mathcer(email.matches()))
        if(password.isEmpty())
        {
            editpass.setError("Password Required!");
            editpass.requestFocus();
            return;
        }
        if (password.length()<6){
            editpass.setError("Minimum 6 char required ");
            editpass.requestFocus();
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    User user = new User(fullname,email);

                    FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Register.this, "User has been registered Succesfully!", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                                startActivity(new Intent(Register.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(Register.this, "Failed to register! Try again", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                            }
                        }
                    });
                }else{
                    Toast.makeText(Register.this, "Failed to register! Try again", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                }
            }
        });

    }
}