package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChoixActivity extends AppCompatActivity {

    public static final String NAME_KEY = "name_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);

        //Récupération du nom&prénom de l'user
        String nom = getIntent().getStringExtra(NAME_KEY);
        if (nom!=null) {
            TextView textNom = findViewById(R.id.choix_TextUser);
            textNom.setText(nom);
        }
    }

    public void onCalcul(View view) {
        // Création d'une intention
        Intent intentMathematique = new Intent(this, MathematiquesActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(intentMathematique);
    }

    public void onQuestion(View view) {
        // Création d'une intention
        Intent intentQuestions = new Intent(this, QuestionActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(intentQuestions);
    }
}