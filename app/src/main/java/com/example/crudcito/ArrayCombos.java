package com.example.crudcito;

import java.util.ArrayList;

public class ArrayCombos {
    ArrayList<Basurita> Adatitos = new ArrayList<>();

    public void agregar(int dato) {
        int clave[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String nombres[] = {"LEBRON", "JA", "KD", "GIANNIS", "KYRIE", "BOOKER", "HARDEN", "MICHEL",
                "DAME", "TRAE"};
        if (dato == 1) {
            for (int i = 0; i < 6; ++i) {
                Basurita objetito = new Basurita();
                objetito.setClave(clave[i]);
                objetito.setNombre(nombres[i]);
                Adatitos.add(objetito);

            }

        } else if (dato == 2) {
            for (int i = 6; i < 10; ++i) {
                Basurita objetito = new Basurita();
                objetito.setClave(clave[i]);
                objetito.setNombre(nombres[i]);
                Adatitos.add(objetito);
            }

        }

        }

    public ArrayList <Basurita> regresar(){
        return Adatitos;
    }

}
