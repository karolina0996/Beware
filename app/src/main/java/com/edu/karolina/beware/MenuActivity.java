package com.edu.karolina.beware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    float edad2;
    float masa2;
    float altura2;
    long iduser;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
            sharedPreferences = getSharedPreferences("PREFERENCIAS_BEWARE",MODE_PRIVATE);
            iduser = sharedPreferences.getLong("ID",0);
            Bundle bundle = getIntent().getExtras();
            /*float año = bundle.getFloat("año");
            float mes = bundle.getFloat("mes");
            float dia = bundle.getFloat("dia");
*/

            edad2 = sharedPreferences.getFloat("edad3",0);
            masa2 = sharedPreferences.getFloat("masa3",0);
            altura2 = sharedPreferences.getFloat("altura3",0);
            float imc = (masa2 / ((altura2 / 100) * (altura2 / 100)));

            String a = Float.toString(imc);
            TextView txtCambiado = (TextView) findViewById(R.id.textView3);
            txtCambiado.setText("IMC:\r\n" + a);

            double calorias = (66.473 + (13.752 * masa2) + (5.0033 * altura2) - (6.755 * edad2)) * 1.375;
            double calorias1 = calorias - 500;
            double calorias2 = calorias + 500;

            int cal = (int)calorias;
            int cal1 = (int)calorias1;
            int cal2 = (int)calorias2;

            if (imc < 16) {
                TextView txtCambiado2 = (TextView) findViewById(R.id.textView4);
                txtCambiado2.setText("ESTADO:\r\nAnorexia");

                TextView txtCambiado3 = (TextView) findViewById(R.id.textView2);
                txtCambiado3.setText("CALORIAS SUGERIDAS:\r\n                "+cal2);
            } else if (imc > 40) {
                TextView txtCambiado2 = (TextView) findViewById(R.id.textView4);
                txtCambiado2.setText("ESTADO:\r\nObesidad morbida");

                TextView txtCambiado3 = (TextView) findViewById(R.id.textView2);
                txtCambiado3.setText("CALORIAS SUGERIDAS:\r\n                "+cal1);
            } else if (imc > 35) {
                TextView txtCambiado2 = (TextView) findViewById(R.id.textView4);
                txtCambiado2.setText("ESTADO:\r\nObesidad moderada");

                TextView txtCambiado3 = (TextView) findViewById(R.id.textView2);
                txtCambiado3.setText("CALORIAS SUGERIDAS:\r\n                "+cal1);
            } else if (imc > 30) {
                TextView txtCambiado2 = (TextView) findViewById(R.id.textView4);
                txtCambiado2.setText("ESTADO:\r\nObesidad leve");

                TextView txtCambiado3 = (TextView) findViewById(R.id.textView2);
                txtCambiado3.setText("CALORIAS SUGERIDAS:\r\n                "+cal1);
            } else if (imc > 25) {
                TextView txtCambiado2 = (TextView) findViewById(R.id.textView4);
                txtCambiado2.setText("ESTADO:\r\nSobrepeso");

                TextView txtCambiado3 = (TextView) findViewById(R.id.textView2);
                txtCambiado3.setText("CALORIAS SUGERIDAS:\r\n                "+cal1);
            } else {
                TextView txtCambiado2 = (TextView) findViewById(R.id.textView4);
                txtCambiado2.setText("ESTADO:\r\nPeso ideal");

                TextView txtCambiado3 = (TextView) findViewById(R.id.textView2);
                txtCambiado3.setText("CALORIAS SUGERIDAS:\r\n                "+cal);
            }

        }

        public void Clicked1(View v) {
            Intent nextActivity = new Intent(this, com.edu.karolina.beware.PesoQueDeseoActivity.class);
            nextActivity.putExtra("edad3", edad2);
            nextActivity.putExtra("altura3", altura2);
            nextActivity.putExtra("masa3", masa2);
            startActivity(nextActivity);
            finish();

        }

        public void Clicked2(View v) {
            Bundle bundle = getIntent().getExtras();



            Intent nextActivity = new Intent(this, com.edu.karolina.beware.CambieDePesoActivity.class);
            nextActivity.putExtra("iduser", iduser);
            nextActivity.putExtra("edad3", edad2);
            nextActivity.putExtra("altura3", altura2);
            nextActivity.putExtra("masa3", masa2);
            startActivity(nextActivity);
            finish();
        }

        public void Clicked3(View v) {


            float imc = (masa2 / ((altura2 / 100) * (altura2 / 100)));
            double calorias = (66.473 + (13.752 * masa2) + (5.0033 * altura2) - (6.755 * edad2)) * 1.375;


            Intent nextActivity = new Intent(this,com.edu.karolina.beware.RegistroComida.class);
            nextActivity.putExtra("edad3", edad2);
            nextActivity.putExtra("altura3", altura2);
            nextActivity.putExtra("masa3", masa2);
            nextActivity.putExtra("imc1", imc);
            nextActivity.putExtra("cx", calorias);


            startActivity(nextActivity);
            finish();
        }
    public void Clicked4(View v) {
        Intent nextActivity = new Intent(this, com.edu.karolina.beware.CalendarioActivity.class);
        startActivity(nextActivity);
        finish();
    }

    }



