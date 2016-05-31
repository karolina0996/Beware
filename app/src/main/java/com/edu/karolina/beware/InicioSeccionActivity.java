package com.edu.karolina.beware;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.karolina.beware.model.adapter.UserDbAdapter;

public class InicioSeccionActivity extends Activity {

    private UserDbAdapter userDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_seccion);

        userDbAdapter = new UserDbAdapter(this);
        userDbAdapter.open();

    }

    public void registerClickedinicio(View v) {

        Intent nextActivity = new Intent(this, RegistroActivity.class);
        startActivity(nextActivity);
        finish();
    }

    public Cursor registerClickedlogin(View v) {

        Cursor micursor = userDbAdapter.consultadatos(((EditText) findViewById(R.id.editText1)).getText().toString());

        try {
            if (micursor.moveToFirst()) {

                String pass = micursor.getString(3);
                int iduser = micursor.getInt(8);
                float edad = Float.parseFloat(micursor.getString(4));
                float altura = Float.parseFloat(micursor.getString(6));
                float masa = Float.parseFloat(micursor.getString(7));

                if (pass.equals(((EditText) findViewById(R.id.editText2)).getText().toString())) {

                    Intent nextActivity = new Intent(this, MenuActivity.class);
                    nextActivity.putExtra("iduser", iduser);
                    nextActivity.putExtra("edad1", edad);
                    nextActivity.putExtra("altura1", altura);
                    nextActivity.putExtra("masa1", masa);
                    startActivity(nextActivity);
                    finish();

                } else {

                    Toast.makeText(this, "Password incorrecto",
                            Toast.LENGTH_LONG).show();
                }

            }

            else {
                Toast.makeText(
                        this,
                        "Este usuario no existe. Escribe otro usuario o regístrate",
                        Toast.LENGTH_LONG).show();

            }

        } catch (Exception ex) {

            Log.e("Base de Datos", "Error al leer la Base de Datos");
        }

        return micursor;

    }

}
