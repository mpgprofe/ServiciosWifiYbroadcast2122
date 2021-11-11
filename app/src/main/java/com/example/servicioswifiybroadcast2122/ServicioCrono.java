package com.example.servicioswifiybroadcast2122;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

public class ServicioCrono extends Service {
    Timer temporizador = new Timer();
    int INTERVALO = 100;
    static MainActivity mainActivity;
    Handler handler;
    float contador;

    public static void setActividadPrincipal(MainActivity ma){
        mainActivity = ma;
    }

    public ServicioCrono() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                mainActivity.actualizarCrono(String.format("%.2f", contador));
            }
        };
        iniciarCrono();
    }

    private void iniciarCrono() {
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                contador+=0.01;
                handler.sendEmptyMessage(0);
            }
        },0, INTERVALO);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        temporizador.cancel();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}