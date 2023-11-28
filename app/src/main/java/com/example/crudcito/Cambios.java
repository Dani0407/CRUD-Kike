package com.example.crudcito;

import android.content.ContentValues;
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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Cambios extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText clave,nombre;
    String clav,nom, comM, comJ,tall, comP;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambios);

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
    public int Marquita(String ala){
        int escoge = 0;
        if (ala.equals("Nike")){
            escoge = 1;
        } else if (ala.equals("Adidas")) {
            escoge = 2;
        }
        return escoge;
    }
    public int jugadorNike(String ala){
        int escoge = 0;
        if (ala.equals("LEBRON")){
            escoge = 1;
        } else if (ala.equals("JA")) {
            escoge = 2;
        }else if (ala.equals("KD")){
            escoge = 3;
        }
        else if(ala.equals("GIANNIS")){
            escoge = 4;
        }
        else if(ala.equals("KYRIE")){
            escoge = 5;
        }
        else if(ala.equals("BOOKER")){
            escoge = 6;
        }

        return escoge;
    }
    public int jugadorAdid(String ala){
        int escoge = 0;
        if (ala.equals("HARDEN")){
            escoge = 1;
        } else if (ala.equals("MICHEL")) {
            escoge = 2;
        }else if (ala.equals("DAME")){
            escoge = 3;
        }
        else if(ala.equals("TRAE")){
            escoge = 4;
        }

        return escoge;
    }
    public void talo (String ala){
        if (ala.equals("Bajo")){
            radb.setChecked(true);

        }else if (ala.equals("Medio")){
            radm.setChecked(true);
        }
        else if(ala.equals("Alto")){
            rada.setChecked(true);
        }
    }
    public int precio(String ala){
        int escoge = 0;
        if (ala.equals("$1000")){
            escoge = 1;
        } else if (ala.equals("$2000")) {
            escoge = 2;
        }else if (ala.equals("$3000")){
            escoge = 3;
        }

        return escoge;
    }
    public void Editar() {
        clave.setEnabled(false);
        nombre.setEnabled(true);
        comboJugador.setEnabled(true);
        comboMarca.setEnabled(true);
        comboPrecio.setEnabled(true);
        radiog.setEnabled(true);
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
    public void onClick(View view) {
        String cadenita = ((Button) view).getText().toString();
        if (cadenita.equals("Regresar")) {
            Intent intentito = new Intent(this, MainActivity.class);
            startActivity(intentito);
        } else if (cadenita.equals("Cambios")) {
            String mensajito = "";
            if (clave.equals("")) {
                mensajito = "Debes llenar la clave del producto";
            } else {
                Base admin = new Base(this, "administrador", null, 1);
                SQLiteDatabase basededatos = admin.getReadableDatabase();
                String codiguito = clave.getText().toString();
                Cursor fila = basededatos.rawQuery("select nombre, marca,jugador,talon,precio from articulos where clave = " + codiguito, null);
                if (fila.moveToFirst()) {
                    Editar();
                    nombre.setText(fila.getString(0));
                    comboMarca.setSelection(Marquita(fila.getString(1)));
                    if (fila.getString(1).equals("Nike")) {
                        String[] jugadoresNike = {"LEBRON", "JA", "KD", "GIANNIS", "KYRIE", "BOOKER"};
                        ArrayAdapter<String> listaAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jugadoresNike);
                        comboJugador.setAdapter(listaAdapter1);
                        comboJugador.setSelection(jugadorNike(fila.getString(2)));
                    } else if (fila.getString(1).equals("Adidas")) {
                        String[] jugadoresAdidas = {"HARDEN", "MICHEL", "DAME", "TRAE"};
                        ArrayAdapter<String> listaAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jugadoresAdidas);
                        comboJugador.setAdapter(listaAdapter3);
                        comboJugador.setSelection(jugadorAdid(fila.getString(2)));
                    }
                    talo((fila.getString(3)));
                    ArrayAdapter<String> listaAdapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, llamada);
                    comboPrecio.setAdapter(listaAdapter5);
                    comboPrecio.setSelection(precio(fila.getString(4)));

                    mensajito = "estos son los datos";
                    agregar.setText("Guardar");
                    basededatos.close();
                }
                mensajito = "no existe el registro";
            }
            AlertDialog.Builder mensa = new AlertDialog.Builder(this);
            mensa.setTitle("DATOS ENCONTRADOS");
            mensa.setMessage(mensajito);
            mensa.setPositiveButton("aceptar", null);
            AlertDialog dialog= mensa.create();
            dialog.show();
    }   else if (cadenita.equals("Guardar")) {
            Base admin = new Base(this, "administrador", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            clav = clave.getText().toString();
            tall = taloo();
            nom = nombre.getText().toString();
            comM = comboMarca.getSelectedItem().toString();
            comJ = comboJugador.getSelectedItem().toString();
            comP = comboPrecio.getSelectedItem().toString();
            if (!clav.equals("")
                    && !tall.equals("")
                    && !nom.equals("")
                    && !comM.equals("Selecciona")
                    && !comJ.equals("Selecciona")
                    && !comP.equals("Selecciona")){
                ContentValues registro = new ContentValues();
                registro.put("nombre",nom);
                registro.put("marca",comM);
                registro.put("jugador",comJ);
                registro.put("talon",tall);
                registro.put("precio",comP);


                int cant = bd.update("articulos", registro, "clave=" + clav, null);
                bd.close();
                if (cant == 1){
                    Toast.makeText(this, "Haz Modificado los datos", Toast.LENGTH_SHORT).show();
                    Limpito();
                }
            }

        }
    }
    public String taloo(){
        String cual = null;
        if (radb.isChecked()) {
            cual = "Bajo";
        } else if (radm.isChecked()) {
            cual = "Medio";
        }
        else if (rada.isChecked()) {
            cual = "Alto";
        }
        return cual;
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
