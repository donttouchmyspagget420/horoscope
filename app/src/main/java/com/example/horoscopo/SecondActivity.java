package com.example.horoscopo;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    private void btnsSetup(){
        Button btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker picker = findViewById(R.id.date);

                Intent prevIntent = getIntent();
                String nombre = prevIntent.getStringExtra("nombre");

                int month = picker.getMonth();
                int day = picker.getDayOfMonth();

                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombre);
                bundle.putInt("month",month);
                bundle.putInt("day",day);

                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

        prevBtnSetup();
    }

    private void prevBtnSetup(){
        Button prev = findViewById(R.id.prev);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        btnsSetup();
    }
}