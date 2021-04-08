package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MathematiquesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematique);
    }

    public void onExerciceMathematique(View view) {
        //Récupérations des éléments nécéssaires depuis le .xml
        TextView consignes = findViewById(R.id.textConsignes);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        Switch timer = findViewById(R.id.SwitchTimer);
        EditText nbCalculs = findViewById(R.id.nbCalcul);
        String operation;
        if (radioGroup.getCheckedRadioButtonId() == R.id.Division) {
            operation = " / ";
            consignes.setText("Vous avez choisi la division \n Tout les résultats à trouver sont entiers et positifs");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Soustraction) {
            operation = " - ";
            consignes.setText("Vous avez choisi la soustraction \n Tout les résultats à trouver sont entiers et positifs");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Multiplication) {
            operation = " x ";
            consignes.setText("Vous avez choisi la multiplication \n Tout les résultats à trouver sont entiers et positifs");
        } else {
            operation = " + ";
            consignes.setText("Vous avez choisi l'addition \n Tout les résultats à trouver sont entiers et positifs");
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

        Intent ExerciceAddition = new Intent(this, ExerciceMathematiquesActivity.class);
        ExerciceAddition.putExtra(ExerciceMathematiquesActivity.OP1_KEY, choixOp1);
        ExerciceAddition.putExtra(ExerciceMathematiquesActivity.OP2_KEY, choixOp2);
        ExerciceAddition.putExtra(ExerciceMathematiquesActivity.OPERATION_KEY, operation);
        ExerciceAddition.putExtra(ExerciceMathematiquesActivity.TIMER_KEY, timer.isChecked());
        //Si l'utilisateur n'a rien saisi dans le champ, on met la valeur à 10
        if (nbCalculs.getText().toString().compareTo("")==0) {
            nbCalculs.setText("10");
        }
        ExerciceAddition.putExtra(ExerciceMathematiquesActivity.NBCALCULS_KEY, Integer.parseInt(nbCalculs.getText().toString()));
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
            consignes.setText("Vous avez choisi la division \n Tout les résultats à trouver sont entiers et positifs");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Soustraction) {
            consignes.setText("Vous avez choisi la soustraction \n Tout les résultats à trouver sont entiers et positifs");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Multiplication) {
            consignes.setText("Vous avez choisi la multiplication \n Tout les résultats à trouver sont entiers et positifs");
        } else {
            consignes.setText("Vous avez choisi l'addition \n Tout les résultats à trouver sont entiers et positifs");
        }
    }
}