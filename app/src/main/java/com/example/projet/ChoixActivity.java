package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChoixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);
    }

    public void onTableMultiplication(View view) {
        // Création d'une intention
        Intent TableMultiplication = new Intent(this, TableMultiplicationActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(TableMultiplication);
    }

    public void onAddition(View view) {
        // Création d'une intention
        Intent Addition = new Intent(this, AdditionActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(Addition);
    }

    public void onQuestion(View view) {
        // Création d'une intention
        Intent Questions = new Intent(this, QuestionActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(Questions);
    }
}