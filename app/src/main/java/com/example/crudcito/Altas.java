package com.example.crudcito;

import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Altas extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText nombre;

    RadioGroup radiog;
    RadioButton radb,radm,rada;
    Button regresar,agregar;
    Spinner comboMarca,comboJugador,comboPrecio;
    ArrayAdapter<String>aadeptos;
    ArrayAdapter<Basurita>aaprods;
    ArrayList<Basurita> Arecibe = new ArrayList<>();
    String [] llamada = {"Selecciona", "$1000", "$2000", "$3000"};
    ArrayAdapter adaptadito;
    int seleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altas);

        nombre = findViewById(R.id.editNombre);

        radiog = findViewById(R.id.radiog);
        radb = findViewById(R.id.radb);
        radm = findViewById(R.id.radm);
        rada = findViewById(R.id.rada);


        agregar = findViewById(R.id.agregar);
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
    private void Limpito(){
        nombre.setText("");
        radiog.clearCheck();
        nombre.setText("");
        ArrayAdapter<String> limptodi = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        limptodi.add("");
        comboJugador.setAdapter(limptodi);
        comboMarca.setAdapter(limptodi);
        comboPrecio.setAdapter(limptodi);
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

    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();
        if (cadenita.equals("Regresar")) {
            Intent intentito = new Intent(this, MainActivity.class);
            startActivity(intentito);
        }
        else
            if(cadenita.equals("AGREGAR")){
                int selectedRadioButtonId = radiog.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                Base admin = new Base(this, "administrador", null, 1);
                SQLiteDatabase basededatos = admin.getWritableDatabase();
                String nom = nombre.getText().toString();
                String mar = comboMarca.getSelectedItem().toString();
                String jug = comboJugador.getSelectedItem().toString();
                String tal = selectedRadioButton.getText().toString();
                String pre = comboPrecio.getSelectedItem().toString();

                ContentValues registro = new ContentValues();
                registro.put("nombre",nom);
                registro.put("marca",mar);
                registro.put("jugador",jug);
                registro.put("talon",tal);
                registro.put("precio",pre);

                basededatos.insert("articulos", null, registro);
                basededatos.close();
                Toast.makeText(this, "Articulo Dado de Alta", Toast.LENGTH_SHORT).show();
                Limpito();

            }

    }
}
