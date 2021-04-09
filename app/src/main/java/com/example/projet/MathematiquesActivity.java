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
        RadioGroup radioGroup = findViewById(R.id.maths_radio_group);
        Switch timer = findViewById(R.id.maths_SwitchTimer);
        EditText nbCalculs = findViewById(R.id.maths_nbCalcul);
        String operation;
        if (radioGroup.getCheckedRadioButtonId() == R.id.maths_Division) {
            operation = " / ";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.maths_Soustraction) {
            operation = " - ";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.maths_Multiplication) {
            operation = " x ";
        } else {
            operation = " + ";
        }

        //Récupération des checkbox
        CheckBox checkOp1C = findViewById(R.id.maths_checkOp1C);
        CheckBox checkOp1D = findViewById(R.id.maths_checkOp1D);
        CheckBox checkOp2C = findViewById(R.id.maths_checkOp2C);
        CheckBox checkOp2D = findViewById(R.id.maths_checkOp2D);
        //Et mise en place des choixOpérateur, ex:99 signifie que le modèle choisira un nombre au hasard entre 0 et 99
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

        Intent intentExerciceMaths = new Intent(this, ExerciceMathematiquesActivity.class);
        intentExerciceMaths.putExtra(ExerciceMathematiquesActivity.OP1_KEY, choixOp1);
        intentExerciceMaths.putExtra(ExerciceMathematiquesActivity.OP2_KEY, choixOp2);
        intentExerciceMaths.putExtra(ExerciceMathematiquesActivity.OPERATION_KEY, operation);
        intentExerciceMaths.putExtra(ExerciceMathematiquesActivity.TIMER_KEY, timer.isChecked());
        //Si l'utilisateur n'a rien saisi dans le champ, on met la valeur à 10
        if (nbCalculs.getText().toString().compareTo("")==0) {
            nbCalculs.setText("10");
        }
        intentExerciceMaths.putExtra(ExerciceMathematiquesActivity.NBCALCULS_KEY, Integer.parseInt(nbCalculs.getText().toString()));
        startActivity(intentExerciceMaths);

    }

    public void onVerif(View view) {
        //Méthode pour que l'user ne puisse pas cocher ou décocher certaines checkbox en fonction de l'état des autres
        CheckBox checkOp1C = findViewById(R.id.maths_checkOp1C);
        CheckBox checkOp1D = findViewById(R.id.maths_checkOp1D);
        CheckBox checkOp1U = findViewById(R.id.maths_checkOp1U);
        CheckBox checkOp2C = findViewById(R.id.maths_checkOp2C);
        CheckBox checkOp2D = findViewById(R.id.maths_checkOp2D);
        CheckBox checkOp2U = findViewById(R.id.maths_checkOp2U);

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
        //Change le texte du haut en fonction du mode de calcul
        TextView consignes = findViewById(R.id.maths_textConsignes);
        RadioGroup radioGroup = findViewById(R.id.maths_radio_group);
        TextView textOp = findViewById(R.id.maths_textOperande);
        if (radioGroup.getCheckedRadioButtonId() == R.id.maths_Division) {
            consignes.setText("Vous avez choisi la division \n Tout les résultats à trouver sont entiers et positifs");
            textOp.setText("/");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.maths_Soustraction) {
            consignes.setText("Vous avez choisi la soustraction \n Tout les résultats à trouver sont entiers et positifs");
            textOp.setText("-");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.maths_Multiplication) {
            consignes.setText("Vous avez choisi la multiplication \n Tout les résultats à trouver sont entiers et positifs");
            textOp.setText("*");
        } else {
            consignes.setText("Vous avez choisi l'addition \n Tout les résultats à trouver sont entiers et positifs");
            textOp.setText("+");
        }
    }
}