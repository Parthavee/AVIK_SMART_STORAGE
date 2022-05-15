package com.example.smartstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Wheat extends AppCompatActivity implements View.OnClickListener {
    private Button quantity,quality,rodent,mositure;
    private TextView t10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheat);
        quantity = (Button) findViewById(R.id.quantity);
        quantity.setOnClickListener(this);

        quality = (Button) findViewById(R.id.quality);
        quality.setOnClickListener(this);

        rodent = (Button) findViewById(R.id.rodent);
        rodent.setOnClickListener(this);

        mositure = (Button) findViewById(R.id.mositure);
        mositure.setOnClickListener(this);

        t10 = (TextView) findViewById(R.id.Wheat);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.quantity:
                startActivity(new Intent(this,Quantity.class));
                break;
            case R.id.rodent:
                startActivity(new Intent(this,Rodent.class));
                break;
        }
    }
}