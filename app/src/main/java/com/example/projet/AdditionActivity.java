package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projet.Data.Addition;

import java.util.ArrayList;
import java.util.Random;

public class AdditionActivity extends AppCompatActivity {

    private Addition addition;
    private static final Random random = new Random();
    private EditText resultat;
    int nbErreur = 0;
    int nbAdditions = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        addition = new Addition(random.nextInt(50), random.nextInt(50));

        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);

        LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

        TextView calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
        calcul.setText(addition.getOperande1() + " x " + addition.getOperande2() + " = ");

        resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);

        linear.addView(linearTMP);
    }

    public void onResult(View view) {
        //Si il n'y a plus d'additions à afficher
        if (nbAdditions==0) {
            if (nbErreur==0) {
                Intent intent = new Intent(this, FelicitationActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, ErreurActivity.class);
                intent.putExtra(ErreurActivity.ERREUR_KEY, nbErreur);
                startActivity(intent);
            }
        } else {
            if (resultat.getText().toString().compareTo("")==0) {
                nbErreur++;
            } else {
                addition.setRepUser(Integer.parseInt(resultat.getText().toString()));
                if (!addition.resultatAddition()) {
                    nbErreur++;
                }
            }
        }
    }
}