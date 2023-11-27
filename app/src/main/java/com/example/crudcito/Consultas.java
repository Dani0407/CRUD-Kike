package com.example.crudcito;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Consultas extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText clave,nombre;

    RadioGroup radiog;
    RadioButton radb,radm,rada;
    Button regresar,agregar;
    Spinner comboMarca,comboJugador,comboPrecio;
    ArrayAdapter<String> aadeptos;
    ArrayAdapter<Basurita>aaprods;
    ArrayList<Basurita> Arecibe = new ArrayList<>();
    String [] llamada = {"Selecciona", "$1000", "$2000", "$3000"};
    ArrayAdapter adaptadito;
    int seleccionado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultas);

        clave = findViewById(R.id.editClave);
        nombre = findViewById(R.id.editNombre);

        radiog = findViewById(R.id.radiog);
        radb = findViewById(R.id.radb);
        radm = findViewById(R.id.radm);
        rada = findViewById(R.id.rada);


        agregar = findViewById(R.id.consultar);
        agregar.setOnClickListener(this);
        regresar = findViewById(R.id.regresar);
        regresar.setOnClickListener(this);

        comboMarca = findViewById(R.id.comboMarca);
        comboJugador = findViewById(R.id.comboJugador);
        comboMarca.setOnItemSelectedListener(this);
        comboJugador.setOnItemSelectedListener(this);
        String deptos[] = {"Selecciona la Marca","Nike","Adidas",};
        aadeptos = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, deptos);
        comboMarca.setAdapter(aadeptos);
        comboPrecio = findViewById(R.id.comboPrecio);
        adaptadito = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, llamada);
        comboPrecio.setOnItemSelectedListener(this);
        comboPrecio.setAdapter(adaptadito);
    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();
        if (cadenita.equals("Regresar")) {
            Intent intentito = new Intent(this, MainActivity.class);
            startActivity(intentito);
        }
        else
            if(cadenita.equals("Consulta")){
                String mensajito = "";
                if (clave.equals("")) {
                    mensajito = "debes llenar el dato";
                } else {
                    Base admin = new Base(this, "administrador", null, 1);
                    SQLiteDatabase basededatos = admin.getReadableDatabase();
                    String codiguito = clave.getText().toString();
                    Cursor fila = basededatos.rawQuery("select nombre, marca,jugador,talon,precio from articulos where codigo = " + codiguito, null);
                    if(fila.moveToFirst()){
                        nombre.setText(fila.getString(0));
                        comboMarca.setSelection(1);
                        mensajito = "estos son los datos";
                        basededatos.close();
                    }
                    else
                        mensajito = "no existe el registro";
                }
            }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(adapterView.getId() == R.id.comboMarca){
            seleccionado = comboMarca.getSelectedItemPosition();
            ArrayCombos aregreso = new ArrayCombos();
            if(seleccionado == 1){
                aregreso.agregar(1);
                Arecibe = aregreso.regresar();
                aaprods = new ArrayAdapter<Basurita>(this,
                        android.R.layout.simple_spinner_item,Arecibe);
                comboJugador.setAdapter(aaprods);
            }
            else
            if(seleccionado == 2){
                aregreso.agregar(2);
                Arecibe = aregreso.regresar();
                aaprods = new ArrayAdapter<Basurita>(this,
                        android.R.layout.simple_spinner_item,Arecibe);
                comboJugador.setAdapter(aaprods);
            }

            if(seleccionado==0){
                comboJugador.setAdapter(null);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
