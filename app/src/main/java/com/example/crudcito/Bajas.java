package com.example.crudcito;

import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Bajas extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Button bajar, regresar;
    EditText clave, nombre;
    Spinner marca;
    ArrayAdapter<String> aadeptos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bajas);

        clave = findViewById(R.id.editClave);
        nombre = findViewById(R.id.editNombre);

        bajar = findViewById(R.id.bajar);
        bajar.setOnClickListener(this);
        regresar = findViewById(R.id.regresar);
        regresar.setOnClickListener(this);

        marca = findViewById(R.id.comboMarca);
        marca.setOnItemSelectedListener(this);
        String deptos[] = {"Selecciona la Marca", "Nike", "Adidas",};
        aadeptos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deptos);
        marca.setAdapter(aadeptos);


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
    private void Limpito(){
        clave.setText("");
        nombre.setText("");
        ArrayAdapter<String> limptodi = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        limptodi.add("");
        marca.setAdapter(limptodi);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button) view).getText().toString();
        if (cadenita.equals("Regresar")) {
            Intent intentito = new Intent(this, MainActivity.class);
            startActivity(intentito);
        } else if (cadenita.equals("Eliminar")) {
            String mensajito = "";
            if (clave.getText().toString().equals("")) {
                Toast.makeText(this, "Datos invalidos, Intenta de nuevo", Toast.LENGTH_SHORT).show();
            } else {
                Base admin = new Base(this, "administrador", null, 1);
                SQLiteDatabase basededatos = admin.getReadableDatabase();
                String codi = clave.getText().toString();
                Cursor fila = basededatos.rawQuery("select nombre, marca from articulos where clave = " + codi, null);
                if (fila.moveToFirst()) {
                    nombre.setText(fila.getString(0));
                    marca.setSelection(Marquita(fila.getString(1)));
                    mensajito= "";
                    bajar.setText("Borrar");
                    basededatos.close();
                }else {
                    mensajito = "No se encuentra el registro";
                }
                AlertDialog.Builder mensa = new AlertDialog.Builder(this);
                mensa.setTitle("DATOS ENCONTRADOS, Seguro de Querer Borrarlos");
                mensa.setMessage(mensajito);
                mensa.setPositiveButton("Aceptar", null);
                mensa.setNegativeButton("Cancelar",null);
                AlertDialog dialog= mensa.create();
                dialog.show();
            }
        } else if (cadenita.equals("Borrar")) {
            Base admin = new Base(this, "administrador", null, 1);
            SQLiteDatabase basededatos = admin.getWritableDatabase();
            String codb =(clave.getText().toString());
            int cantidad = basededatos.delete("articulos", "clave=?", new String[]{codb});
            basededatos.close();
            if (cantidad==1){
                Toast.makeText(this, "Eliminado de la Base", Toast.LENGTH_SHORT).show();
                Limpito();
            }

                }
            }
        }


