package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projet.Data.Multiplication;
import com.example.projet.Data.TableMultiplication;

import java.util.ArrayList;

public class ExerciceTableActivity extends AppCompatActivity {

    public static final String NUMERO_KEY = "numero_key";
    private TableMultiplication tableMultiplication;
    private ArrayList<EditText> resultats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_table);
        int numero = getIntent().getIntExtra(NUMERO_KEY, 0);

        tableMultiplication = new TableMultiplication(numero);

        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        for (Multiplication multiplication : tableMultiplication.getMultiplications()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            TextView calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(multiplication.getOperande1() + " x " + multiplication.getOperande2() + " = ");

            EditText resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);
            resultats.add(resultat);

            linear.addView(linearTMP);
        }

    }

    public void onResult(View view) {
        int numero = getIntent().getIntExtra(NUMERO_KEY, 0);
        int nbErreur = tableMultiplication.getNombreErreurs(resultats);

        if (nbErreur==0) {
            Intent intent = new Intent(this, FelicitationActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ErreurActivity.class);
            intent.putExtra(ErreurActivity.ERREUR_KEY, nbErreur);
            startActivity(intent);
        }
    }
}