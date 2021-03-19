package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class TableMultiplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_table_multiplication);

        NumberPicker np = findViewById(R.id.numberPicker);

        np.setMinValue(1);
        np.setMaxValue(9);

    }

    public void onTable(View view) {
        NumberPicker numeroTable = findViewById(R.id.numberPicker);
        int numero = numeroTable.getValue();

        Intent intent = new Intent(this, ExerciceTableActivity.class);
        intent.putExtra(ExerciceTableActivity.NUMERO_KEY, numero);
        startActivity(intent);
    }

}