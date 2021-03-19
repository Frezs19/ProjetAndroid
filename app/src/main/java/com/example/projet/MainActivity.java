package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onChoix(View view) {
        // Création d'une intention
        Intent ChoixActivity = new Intent(this, ChoixActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(ChoixActivity);
    }
}