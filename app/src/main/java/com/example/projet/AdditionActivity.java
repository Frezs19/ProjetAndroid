package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

public class AdditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
    }

    public void onExerciceAddition(View view) {
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        String operation;
        if (radioGroup.getCheckedRadioButtonId() == R.id.Division) {
            operation = " / ";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Soustraction) {
            operation = " - ";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.Multiplication) {
            operation = " x ";
        } else {
            operation = " + ";
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
}