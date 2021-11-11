package com.example.servicioswifiybroadcast2122;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button play, stop, startCrono, stopCrono;
    TextView textViewCrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.buttonPlay);
        stop = findViewById(R.id.buttonStop);
        startCrono = findViewById(R.id.buttonIniciarCrono);
        stopCrono = findViewById(R.id.buttonStopCrono);
        textViewCrono = findViewById(R.id.textViewCrono);

        ServicioCrono.setActividadPrincipal(this);

        startCrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), ServicioCrono.class));
            }
        });

        stopCrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), ServicioCrono.class));
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), ServicioMp3.class));
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), ServicioMp3.class));
            }
        });
    }
    void actualizarCrono(String cadena){
        textViewCrono.setText(cadena);
    }
}