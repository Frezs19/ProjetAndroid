package com.example.projet;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.projet.db.DatabaseClient;
import com.example.projet.db.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    private DatabaseClient mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        Button boutonCreer = findViewById(R.id.createacc_BoutonCreer);
        boutonCreer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAccount();
            }
        });

    }

    private void createAccount() {
        EditText surNameEdit = findViewById(R.id.createacc_ChampNom);
        String surName = surNameEdit.getText().toString();
        EditText firstNameEdit = findViewById(R.id.createacc_ChampPrenom);
        String firstName = firstNameEdit.getText().toString();


        if (surName.isEmpty()) {
            Toast.makeText(CreateAccountActivity.this, "Veuillez entrez un nom", Toast.LENGTH_LONG).show();
        }

        if (firstName.isEmpty()) {
            Toast.makeText(CreateAccountActivity.this, "Veuillez entrez un prénom", Toast.LENGTH_LONG).show();
        }

        class createAccount extends AsyncTask<Void, Void, Student> {

            @Override
            protected Student doInBackground(Void... voids) {

                // creating a task
                Student student = new Student();
                student.setFirstName(firstName);
                student.setSurName(surName);

                // adding to database
                mDb.getAppDatabase()
                        .studentDao()
                        .insert(student);

                return student;
            }

            @Override
            protected void onPostExecute(Student student) {
                super.onPostExecute(student);

                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Compté créé avec succès", Toast.LENGTH_LONG).show();
            }
        }

        createAccount ca = new createAccount();
        ca.execute();
    }
}