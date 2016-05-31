package com.edu.karolina.beware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PesoQueDeseoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso_que_deseo);
    }


    public void Clickedcalorias(View v) {


        EditText etpeso = (EditText) findViewById(R.id.editText1);
        EditText etdias = (EditText) findViewById(R.id.editText2);

        if(!etpeso.getText().toString().equals("") && Float.parseFloat(etpeso.getText().toString()) > 39 && Float.parseFloat(etpeso.getText().toString()) < 91){

            if(!etdias.getText().toString().equals("") && Float.parseFloat(etdias.getText().toString()) > 14 && Float.parseFloat(etdias.getText().toString()) < 91){

                Bundle bundle = getIntent().getExtras();
                float edad2 = bundle.getFloat("edad3");
                float masax = Float.parseFloat(etpeso.getText().toString());
                float altura2 = bundle.getFloat("altura3");

                double calorias = ((66.473 + (13.752 * masax) + (5.0033 * altura2) - (6.755 * edad2)) * 1.375)-(2*Float.parseFloat(etdias.getText().toString()));
                String b = Double.toString(calorias);
                TextView txtCambiado3 = (TextView) findViewById(R.id.textView4);
                txtCambiado3.setText(b);


            }
            else {
                Toast.makeText(this, "El rango de dias debe estar entre 15 y 90 dias",
                        Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "El rango de peso debe estar entre 40 y 90 kgs",
                    Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

        Bundle bundle = getIntent().getExtras();
        float edad2 = bundle.getFloat("edad3");
        float masa2 = bundle.getFloat("masa3");
        float altura2 = bundle.getFloat("altura3");

        Intent nextActivity = new Intent(this, com.edu.karolina.beware.MenuActivity.class);
        nextActivity.putExtra("edad1", edad2);
        nextActivity.putExtra("altura1", altura2);
        nextActivity.putExtra("masa1", masa2);
        startActivity(nextActivity);
        finish();
    }
}