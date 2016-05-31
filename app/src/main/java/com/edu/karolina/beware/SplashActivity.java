package com.edu.karolina.beware;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



public class SplashActivity extends AppCompatActivity {
    private final String TAG = SplashActivity.class.getSimpleName();

    private Handler handler;
    private Runnable runnable;

    private Context mContext;

    public final int SPLASH_TIME = 700;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Boolean isLoged;
        sharedPreferences = getSharedPreferences("PREFERENCIAS_BEWARE",MODE_PRIVATE);
        isLoged =  sharedPreferences.getBoolean("IS_LOGED",false);
        mContext = this;
        runnable = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                if (isLoged == false) {
                    Intent nextActivity = new Intent(mContext, InicioSeccionActivity.class);
                    startActivity(nextActivity);
                    finish();
                }
                else {
                    Intent nextAtivity = new Intent(mContext, MenuActivity.class);
                    startActivity(nextAtivity);
                    finish();
                }
            }
        };


        handler = new Handler();
        handler.postDelayed(runnable, SPLASH_TIME);

        Log.d(TAG, "Actividad creada");
        Log.d(TAG, "Preparando Transicion en " + SPLASH_TIME + "milisegundos");

    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

        if(handler != null){
            handler.removeCallbacks(runnable);
            Log.d(TAG, "Transicion Cancelada");
        }




    }



}