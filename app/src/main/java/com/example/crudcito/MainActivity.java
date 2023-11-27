package com.example.crudcito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    ArrayList<String> cadenitasA;
    Lista objetito;
    ArrayAdapter adaptatido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objetito = new Lista();
        cadenitasA = new ArrayList<>();
        objetito.agregar();
        cadenitasA = objetito.refreso();
        lista = findViewById(R.id.lista);
        lista.setOnItemClickListener(this);

        adaptatido = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                cadenitasA);
        lista.setAdapter(adaptatido);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String operacionSeleccionada = cadenitasA.get(i);
        if ("ALTAS".equals(operacionSeleccionada)) {
            Intent intentadito = new Intent(this, Altas.class);
            startActivity(intentadito);
        }
        else
        if("BAJAS".equals(operacionSeleccionada)) {
            Intent intentadito = new Intent(this, Bajas.class);
            startActivity(intentadito);
        }
        else
        if("CAMBIOS".equals(operacionSeleccionada)) {
            Intent intentadito = new Intent(this, Cambios.class);
            startActivity(intentadito);
        }
        else
        if("CONSULTAS".equals(operacionSeleccionada)) {
            Intent intentadito = new Intent(this, Consultas.class);
            startActivity(intentadito);
        }

    }
}