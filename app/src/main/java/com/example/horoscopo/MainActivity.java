package com.example.horoscopo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private void btnsSetup(){
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = findViewById(R.id.nombre);
                String nombre = edit.getText().toString();
                if(!nombre.isBlank()){
                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                    intent.putExtra("nombre", nombre);
                    startActivity(intent);
                }
            }
        });

        closeBtnSetup();
    }

    private void closeBtnSetup(){
        Button close = findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnsSetup();
    }
}
