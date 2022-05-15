package com.example.smartstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Rodent extends AppCompatActivity {
    TextView T1;
    ImageView I1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodent);
        T1 = findViewById(R.id.textView4);
        I1 = findViewById(R.id.imageView);


    }
}