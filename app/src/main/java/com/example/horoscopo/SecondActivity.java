package com.example.horoscopo;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");

        Button btn = findViewById(R.id.button2);
        Button prev = findViewById(R.id.prev);
        DatePicker picker = findViewById(R.id.date);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = picker.getYear();
                int month = picker.getMonth();
                int day = picker.getDayOfMonth();

                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombre);
                bundle.putInt("year",year);
                bundle.putInt("month",month);
                bundle.putInt("day",day);

                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}