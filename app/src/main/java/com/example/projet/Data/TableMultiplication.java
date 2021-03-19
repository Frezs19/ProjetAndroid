package com.example.projet.Data;

import android.widget.EditText;

import java.util.ArrayList;

public class TableMultiplication {

    private ArrayList<Multiplication> multiplications = new ArrayList();

    public TableMultiplication(int numeroTable) { initTableMultiplications(numeroTable);}

    public void initTableMultiplications(int numeroTable) {
        for (int i=1; i<=10; i++) {
            multiplications.add(new Multiplication(i, numeroTable));
        }
    }

    public ArrayList<Multiplication> getMultiplications() { return multiplications; }

    public int getNombreErreurs(ArrayList<EditText> reponsesUser) {
        int i=0;
        int nbErreurs=0;
        for (Multiplication multiplication : multiplications) {
            if (reponsesUser.get(i).getText().toString().compareTo("")==0) { //Si la réponse vaut "" c'est que l'user n'a pas rempli et a donc une erreur en plus
                nbErreurs++;
            } else { //Le code qui suit ne doit pas être exécuter si l'user n'a pas saisie de réponse
                multiplication.setRepUser(Integer.parseInt(reponsesUser.get(i).getText().toString()));
                if (!multiplication.resultatMultiplication()) {
                    nbErreurs++;
                }
            }
            i++;
        }
        return nbErreurs;
    }

}
