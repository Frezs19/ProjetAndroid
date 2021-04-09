package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ErreurCultureActivity extends AppCompatActivity {

    public static final String ERREUR_KEY = "erreur_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erreur_culture);
        int nbErreur = getIntent().getIntExtra(ERREUR_KEY, 0);
        TextView textnbErreur = findViewById(R.id.erreurCulture_textErreur);
        textnbErreur.setText("Nombre d'erreurs : " + nbErreur);
    }

    public void onReturn(View view) {
        super.finish();
    }

    public void onMenu(View view ) {
        Intent intentMaths = new Intent(this, ChoixActivity.class);
        intentMaths.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //supprime les activités qui sont au dessus de l'activité qu'on va appeler, dans notre cas on va suprrimer erreur et table
        startActivity(intentMaths);
    }
}