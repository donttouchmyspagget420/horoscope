package com.example.horoscopo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ThirdActivity extends AppCompatActivity {

    private void activity(String filename){
        InputStream stream = null;

        try {
            stream = this.getAssets().open(filename);
        } catch (IOException e) {
            Toast.makeText(this,"no puede abrir el horoscopo",Toast.LENGTH_SHORT).show();
        }

        if(stream != null){
            readJason(stream);
        }
    }

    private void readJason(InputStream stream){
        String json = "";
        try {
            int size = stream.available();
            byte[] buff = new byte[size];
            
            if (stream.read(buff) <= 0) throw new IOException();
            stream.close();
            json = new String(buff, StandardCharsets.UTF_8);
        } catch (IOException e) {
            Toast.makeText(this,"no puede leer el horoscopo",Toast.LENGTH_SHORT).show();
        }
        
        if (!json.isBlank()){
            checkSign(json);
        }
    }

    private void checkSign(String json){

        JSONArray arr = new JSONArray();
        try {
             arr = new JSONArray(json);
        } catch (JSONException e) {
            Toast.makeText(this,"no puede parsear el horoscopo",Toast.LENGTH_SHORT).show();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        if(bundle == null) finish();
        Bundle bundle1 = bundle.getBundle("bundle");

        int month = bundle1.getInt("month");
        int day = bundle1.getInt("day");
        String sign = null;

        for (int i = 0; i < arr.length(); i++){
            JSONObject obj = null;
            try {
                obj = arr.getJSONObject(i);
            } catch (JSONException e) {
                Toast.makeText(this,"no puede parsear el horoscopo",Toast.LENGTH_SHORT).show();
                continue;
            }

            if(calculate(month,day,obj)) showHoroscope(obj,bundle1.getString("nombre"));
        }
    }

    private boolean calculate(int month, int day, JSONObject obj){
        String dateString;
        try {
            dateString = obj.getString("fechas");
        } catch (JSONException e) {
            Toast.makeText(this,"no puede parsear el horoscopo",Toast.LENGTH_SHORT).show();
            return false;
        }

        String[] dates = dateString.split("-");

        String[] earlys = dates[0].split("/");
        String[] lates = dates[1].split("/");

        int target = month * 100 + day;
        int early = Integer.parseInt(earlys[1]) * 100 + Integer.parseInt(earlys[0]);
        int late = Integer.parseInt(lates[1]) * 100 + Integer.parseInt(lates[0]);

        return early >= target && late <= target;
    }

    private void showHoroscope(JSONObject obj,String nombre){

        try {
            ImageView img = findViewById(R.id.img);

            switch (obj.getString("elemento")){
                case "air" : {
                    img.setImageResource(R.drawable.air);
                    break;
                }
                case "fire" : {
                    img.setImageResource(R.drawable.fire);
                    break;
                }
                case "earth" : {
                    img.setImageResource(R.drawable.earth);
                    break;
                }
                case "water" : {
                    img.setImageResource(R.drawable.water);
                    break;
                }
            }
        } catch (Exception e){
            Toast.makeText(this,"no puede mostrar el elemento",Toast.LENGTH_SHORT).show();
        }

        try {
            TextView txt = findViewById(R.id.txt);

           String buff = nombre + "es" + obj.getString("signo") + "\n" +  obj.getString("prediccion");
           txt.setText(buff);
        } catch (Exception e){
            Toast.makeText(this,"no puede mostrar el elemento",Toast.LENGTH_SHORT).show();
        }

    }

    private void prevBtnSetup(){
        Button prev = findViewById(R.id.prev1);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void closeBtnSetup(){
        Button prev = findViewById(R.id.close1);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/dQw4w9WgXcQ?si=XhE8JXhag695bygc"));
                startActivity(intent);
            }
        });
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

        prevBtnSetup();
        closeBtnSetup();
        activity("horoscopo.json");
    }
}