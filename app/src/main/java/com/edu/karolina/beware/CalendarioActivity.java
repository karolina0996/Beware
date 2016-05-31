package com.edu.karolina.beware;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;




public class CalendarioActivity extends AppCompatActivity {
    private int año;
    private int mes;
    private int dia;
    private EditText campoFecha;
    private Button buttonFecha;

    public static DatePickerDialog.OnDateSetListener selectorFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        campoFecha = (EditText) findViewById(R.id.campoFecha);
        buttonFecha = (Button) findViewById(R.id.buttonFecha);
        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar. MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mostrarFecha ();
        selectorFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                año = year;
                mes = monthOfYear;
                dia = dayOfMonth;
                mostrarFecha();
            }
        };




    }

    private void mostrarFecha() {
        campoFecha.setText(año+"/"+mes+"/"+dia);
    }








}




