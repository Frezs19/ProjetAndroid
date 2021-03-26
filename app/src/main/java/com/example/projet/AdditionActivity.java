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
    private ArrayList<LinearLayout> listeAddition = new ArrayList<>();
    private ArrayList<EditText> listeReponses = new ArrayList<>();
    private ArrayList<Addition> listeCalcul = new ArrayList<>();
    private EditText resultat;
    private TextView calcul;
    private LinearLayout linear;
    int nbErreur = 0;
    int nbAdditions = 10;
    int numAddition = 1;
    TextView compteurAddition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        compteurAddition = findViewById(R.id.compteurAddition);
        compteurAddition.setText(numAddition + "/10");

        for (int i=0; i < nbAdditions; i++) {
            addition = new Addition(random.nextInt(10), random.nextInt(1));

            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(addition.getOperande1() + " + " + addition.getOperande2() + " = ");

            resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);

            listeAddition.add(linearTMP);
            listeCalcul.add(addition);
            listeReponses.add(resultat);
        }
        linear = findViewById(R.id.linear);
        linear.addView(listeAddition.get(numAddition-1));
    }

    public void onResult(View view) {
        //Si il a encore des additions à afficher
        if (numAddition!=nbAdditions) {
            linear.removeAllViews();
            numAddition++;
            compteurAddition = findViewById(R.id.compteurAddition);
            compteurAddition.setText(numAddition + "/10");
            linear.addView(listeAddition.get(numAddition-1));
        } else {
            //Vérifie les erreurs
            nbErreur=0;
            int i=0;
            for (Addition addition : listeCalcul) {
                if (listeReponses.get(i).getText().toString().compareTo("")==0) {
                    nbErreur++;
                } else {
                    addition.setRepUser(Integer.parseInt(listeReponses.get(i).getText().toString()));
                    if (!addition.resultatAddition()) {
                        nbErreur++;
                    }
                }
                i++;
            }

            //Sinon on regarde le nombre d'erreur et on affiche la bonne activite
            if (nbErreur == 0) {
                Intent intent = new Intent(this, FelicitationActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, ErreurActivity.class);
                intent.putExtra(ErreurActivity.ERREUR_KEY, nbErreur);
                startActivity(intent);
            }
        }
    }

    public void onPrec(View view) {
        if (numAddition!=1) {
            linear.removeAllViews();
            numAddition--;
            compteurAddition = findViewById(R.id.compteurAddition);
            compteurAddition.setText(numAddition + "/10");
            linear.addView(listeAddition.get(numAddition-1));
        }
    }
}