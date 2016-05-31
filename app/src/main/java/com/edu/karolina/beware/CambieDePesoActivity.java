package com.edu.karolina.beware;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.edu.karolina.beware.model.WeightVO;
import com.edu.karolina.beware.model.adapter.UserDbAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CambieDePesoActivity extends AppCompatActivity {

    private XYPlot mySimpleXYPlot;
    UserDbAdapter adapter;
    int iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambie_de_peso);

        Bundle bundle = getIntent().getExtras();
        iduser = bundle.getInt("iduser");

        adapter = new UserDbAdapter(this);
        graficar();

    }

    private void graficar(){
        adapter.open();
        Cursor cursor = null;

        try{
            cursor = adapter.consultapesos(iduser);
        } catch(Exception e){
            e.printStackTrace();
        }

        ArrayList<com.edu.karolina.beware.model.WeightVO> weights = new ArrayList<com.edu.karolina.beware.model.WeightVO>();

        if(cursor!=null && cursor.moveToFirst()){
            do{
                com.edu.karolina.beware.model.WeightVO object = new com.edu.karolina.beware.model.WeightVO(cursor.getLong(0), cursor.getDouble(1));
                weights.add(object);
            }while(cursor.moveToNext());
        }

        cursor.close();
        adapter.close();

        // Inicializamos el objeto XYPlot b�scandolo desde el layout:
        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        Number[] series1Numbers;
        Number[] series2Numbers;

        try{
            series1Numbers = new Number[weights.size()];
            series2Numbers = new Number[weights.size()];

            int i;
            for(i=0; i<weights.size(); i++){
                series1Numbers[i] = weights.get(i).getDate();
                series2Numbers[i] = weights.get(i).getValue();
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
            series1Numbers = new Number[] {0, 1, 2, 3, 4, 5};
            series2Numbers = new Number[] {0, 0, 0, 0, 0, 0};
        }

        // A�adimos L�nea N�mero UNO:
        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), // Array de datos
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // S�lo valores verticales
                "Series1"); // Nombre de la primera serie

        // Repetimos para la segunda serie
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");



        // Modificamos los colores de la primera serie
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),                   // Color de la l�nea
                Color.rgb(0, 100, 0),               // Color del punto
                Color.rgb(0, 0, 0));              // Relleno

        // Repetimos para la segunda serie
        mySimpleXYPlot.addSeries(series2, new LineAndPointFormatter
                (Color.rgb(0, 0, 200), Color.rgb(0, 0, 100), Color.rgb(150, 150, 190)));

    }

    public void onClickGuardar(View v){
        WeightVO object = new com.edu.karolina.beware.model.WeightVO();

        EditText valueEdit = (EditText) findViewById(R.id.editText1);
        double value = Double.parseDouble(valueEdit.getText().toString());

        object.setIduser(iduser);
        object.setDate(new Date().getTime());
        object.setValue(value);

        adapter.open();
        adapter.guardarpeso(object);
        adapter.close();


        Bundle bundle = getIntent().getExtras();
        float edad2 = bundle.getFloat("edad3");
        float masa2 = bundle.getFloat("masa3");
        float altura2 = bundle.getFloat("altura3");

        Intent intent = new Intent(getApplication(), CambieDePesoActivity.class);
        intent.putExtra("iduser", iduser);
        intent.putExtra("edad3", edad2);
        intent.putExtra("altura3", altura2);
        intent.putExtra("masa3", masa2);
        startActivity(intent);
        finish();
    }

    public void onClickActualizar(View v){

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

}

