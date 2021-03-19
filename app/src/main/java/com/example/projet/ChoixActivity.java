package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
}