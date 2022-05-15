package com.example.smartstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Quantity extends AppCompatActivity {
    EditText Filled;
    EditText Empty;
    TextView T1;
    TextView T2;
    TextView T3;
    TextView T4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        Filled = findViewById(R.id.editTextTextPersonName4);
        Empty = findViewById(R.id.editTextTextPersonName3);
        T1 = findViewById(R.id.textView4);
        T2 = findViewById(R.id.textView5);
        T3 = findViewById(R.id.textView7);
        T4 = findViewById(R.id.textView8);



    }

}