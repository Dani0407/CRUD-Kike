package com.example.crudcito;

import java.util.ArrayList;

public class Lista {
    ArrayList<String> cadenitasA;

    public Lista() {
        cadenitasA = new ArrayList<>();
    }

    public void agregar() {
        cadenitasA.add("ALTAS");
        cadenitasA.add("BAJAS");
        cadenitasA.add("CAMBIOS");
        cadenitasA.add("CONSULTAS");
    }

    public ArrayList<String> refreso() {
        return cadenitasA;
    }
}
