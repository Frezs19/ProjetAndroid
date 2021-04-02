package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AdditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
    }

    public void onExerciceAddition(View view) {
        TextView consignes = findViewById(R.id.textConsignes);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        String operation;
        if (radioGroup.getCheckedRadioButtonId() == R.id.Division) {
            operation = " / ";
            consignes.setText("Vous avez choisi la division /n Tout les résultats à trouver sont entiers et positifs");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Soustraction) {
            operation = " - ";
            consignes.setText("Vous avez choisi la soustraction /n Tout les résultats à trouver sont entiers et positifs");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Multiplication) {
            operation = " x ";
            consignes.setText("Vous avez choisi la multiplication /n Tout les résultats à trouver sont entiers et positifs");
        } else {
            operation = " + ";
            consignes.setText("Vous avez choisi l'addition /n Tout les résultats à trouver sont entiers et positifs");
        }

        CheckBox checkOp1C = findViewById(R.id.checkOp1C);
        CheckBox checkOp1D = findViewById(R.id.checkOp1D);
        CheckBox checkOp2C = findViewById(R.id.checkOp2C);
        CheckBox checkOp2D = findViewById(R.id.checkOp2D);

        int choixOp1;
        int choixOp2;
        if (checkOp1C.isChecked() && checkOp1D.isChecked()) {
            choixOp1 = 999;
        } else if (!checkOp1C.isChecked() && checkOp1D.isChecked()) {
            choixOp1 = 99;
        } else {
            choixOp1 = 9;
        }
        if (checkOp2C.isChecked() && checkOp2D.isChecked()) {
            choixOp2 = 999;
        } else if (!checkOp2C.isChecked() && checkOp2D.isChecked()) {
            choixOp2 = 99;
        } else {
            choixOp2 = 9;
        }

        Intent ExerciceAddition = new Intent(this, ExerciceAdditionActivity.class);
        ExerciceAddition.putExtra(ExerciceAdditionActivity.OP1_KEY, choixOp1);
        ExerciceAddition.putExtra(ExerciceAdditionActivity.OP2_KEY, choixOp2);
        ExerciceAddition.putExtra(ExerciceAdditionActivity.OPERATION_KEY, operation);
        startActivity(ExerciceAddition);

    }

    public void onVerif(View view) {
        CheckBox checkOp1C = findViewById(R.id.checkOp1C);
        CheckBox checkOp1D = findViewById(R.id.checkOp1D);
        CheckBox checkOp1U = findViewById(R.id.checkOp1U);
        CheckBox checkOp2C = findViewById(R.id.checkOp2C);
        CheckBox checkOp2D = findViewById(R.id.checkOp2D);
        CheckBox checkOp2U = findViewById(R.id.checkOp2U);

        if (checkOp1C.isChecked()) {
            checkOp1D.setChecked(true);
            checkOp1U.setChecked(true);
        } else if (checkOp1D.isChecked()) {
            checkOp1C.setChecked(false);
        }

        if (checkOp2C.isChecked()) {
            checkOp2D.setChecked(true);
            checkOp2U.setChecked(true);
        } else if (checkOp2D.isChecked()) {
            checkOp2C.setChecked(false);
        }

        checkOp1U.setChecked(true);
        checkOp2U.setChecked(true);
    }

    public void onModifText(View view) {
        TextView consignes = findViewById(R.id.textConsignes);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        if (radioGroup.getCheckedRadioButtonId() == R.id.Division) {
            consignes.setText("Vous avez choisi la division Tout les résultats à trouver sont entiers et positifs");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Soustraction) {
            consignes.setText("Vous avez choisi la soustraction Tout les résultats à trouver sont entiers et positifs");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Multiplication) {
            consignes.setText("Vous avez choisi la multiplication Tout les résultats à trouver sont entiers et positifs");
        } else {
            consignes.setText("Vous avez choisi l'addition Tout les résultats à trouver sont entiers et positifs");
        }
    }
}