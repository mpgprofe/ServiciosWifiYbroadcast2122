package com.example.servicioswifiybroadcast2122;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button play, stop, startCrono, stopCrono;
    TextView textViewCrono;
    SilaBateriaCambia siLaBateriaCambia = new SilaBateriaCambia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");

        getBaseContext().registerReceiver(siLaBateriaCambia, intentFilter);


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

    void actualizarCrono(String cadena) {
        textViewCrono.setText(cadena);
    }

    private class SilaBateriaCambia extends BroadcastReceiver {
        String ETIQUETA = "ESTADO";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                Toast.makeText(MainActivity.this, "Acabas de conectar el cable.", Toast.LENGTH_SHORT).show();
                Log.i(ETIQUETA, "Acabas de conectar el cable.");
            } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                Toast.makeText(MainActivity.this, "Acabas de desconectar el cable.", Toast.LENGTH_SHORT).show();
                Log.i(ETIQUETA, "Acabas de desconectar el cable.");

            } else if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
                Toast.makeText(MainActivity.this, "Nivel bajo de batería.", Toast.LENGTH_SHORT).show();
                Log.i(ETIQUETA, "Nivel de batería bajo.");

            } else if (intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                Toast.makeText(MainActivity.this, "Tu estado de wifi ha cambiado.", Toast.LENGTH_SHORT).show();
                Log.i(ETIQUETA, "Tu estado de wifi ha cambiado.");

            }

        }
    }
}