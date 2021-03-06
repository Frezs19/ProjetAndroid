package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ErreurActivity extends AppCompatActivity {

    public static final String ERREUR_KEY = "erreur_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erreur);
        int nbErreur = getIntent().getIntExtra(ERREUR_KEY, 0);
        TextView textnbErreur = findViewById(R.id.erreur_textNbErreur);
        textnbErreur.setText("Nombre d'erreurs : " + nbErreur);
    }

    public void onReturn(View view) {
        super.finish();
    }

    public void onChoixMaths(View view ) {
        Intent intentMaths = new Intent(this, MathematiquesActivity.class);
        intentMaths.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //supprime les activités qui sont au dessus de l'activité qu'on va appeler, dans notre cas on va suprrimer erreur et table
        startActivity(intentMaths);
    }
}