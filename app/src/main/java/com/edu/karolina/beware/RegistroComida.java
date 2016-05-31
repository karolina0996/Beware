package com.edu.karolina.beware;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import java.util.ArrayList;



public class RegistroComida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_comida);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

        Bundle bundle = getIntent().getExtras();
        float edad2 = bundle.getFloat("edad3");
        float masa2 = bundle.getFloat("masa3");
        float altura2 = bundle.getFloat("altura3");

        Intent nextActivity = new Intent(this, MenuActivity.class);
        nextActivity.putExtra("edad1", edad2);
        nextActivity.putExtra("altura1", altura2);
        nextActivity.putExtra("masa1", masa2);
        startActivity(nextActivity);
        finish();
    }


    public void cargaListado(ArrayList<String> datos) {
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        ListView listado = (ListView) findViewById(R.id.listView1);
        listado.setAdapter(adaptador);

    }
}






