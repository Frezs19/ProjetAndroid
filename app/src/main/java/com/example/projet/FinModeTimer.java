package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinModeTimer extends AppCompatActivity {

    public static final String NBREUSSI_KEY = "nbreussi_key";
    public static final String TIME_KEY = "time_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_mode_timer);

        int nbCalculReussis = getIntent().getIntExtra(NBREUSSI_KEY, 0);
        int temps = getIntent().getIntExtra(TIME_KEY, 0);

        TextView textResultat = findViewById(R.id.finModeTimer_textResultat);
        textResultat.setText(nbCalculReussis + "\n réponses justes \n en " + temps + " secondes");
    }

    public void onRejouer(View view) {
        Intent intentMathematique = new Intent(this, MathematiquesActivity.class);
        intentMathematique.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentMathematique);
    }

    public void onMainAct(View view) {
        Intent intentMain = new Intent(this, MainActivity.class);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //supprime les activités qui sont au dessus de l'activité qu'on va appeler, dans notre cas on va suprrimer feli et table et act5
        startActivity(intentMain);
    }
}