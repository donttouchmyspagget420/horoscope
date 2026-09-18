package com.example.horoscopo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ThirdActivity extends AppCompatActivity {

    private void activity(){
        InputStream stream = null;

        try {
            stream = this.getAssets().open("horoscopo.json");
        } catch (IOException e) {
            Toast.makeText(this,"no puede leer el horoscopo",Toast.LENGTH_SHORT).show();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        if(stream != null){
            readJason(stream);
        }
    }

    private void readJason(InputStream stream){}

    private String checkSign(){
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.third), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button prev = findViewById(R.id.prev1);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activity();
    }
}