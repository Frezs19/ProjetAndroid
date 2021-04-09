package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FelicitationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felicitation);
    }

    public void onRecommencer(View view) {
        Intent intentMaths = new Intent(this, MathematiquesActivity.class);
        intentMaths.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //supprime les activités qui sont au dessus de l'activité qu'on va appeler, dans notre cas on va suprrimer feli et table
        startActivity(intentMaths);
    }

    public void onChoixAct(View view) {
        Intent intentChoix = new Intent(this, ChoixActivity.class);
        intentChoix.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //supprime les activités qui sont au dessus de l'activité qu'on va appeler, dans notre cas on va suprrimer feli et table et act5
        startActivity(intentChoix);
    }
}