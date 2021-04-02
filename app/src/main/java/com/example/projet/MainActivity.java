package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projet.db.DatabaseClient;
import com.example.projet.db.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseClient mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        Button boutonConnexion = findViewById(R.id.main_BoutonConnexion);
        boutonConnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkStudent();
            }
        });

        Button boutonCreationCOmpte = findViewById(R.id.main_BoutonCreationCompte);
        boutonCreationCOmpte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Création d'une intention
                Intent CreateAccountActivity = new Intent(MainActivity.this, CreateAccountActivity.class);

                // Lancement de la demande de changement d'activité
                startActivity(CreateAccountActivity);
            }
        });
    }

    public void onBoutonAnonyme(View view) {
        // Création d'une intention
        Intent ChoixActivity = new Intent(this, ChoixActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(ChoixActivity);
    }

    private void checkStudent() {
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class getStudents extends AsyncTask<Void, Void, List<Student>> {

            @Override
            protected List<Student> doInBackground(Void... voids) {
                List<Student> studentList = mDb.getAppDatabase()
                        .studentDao()
                        .getAll();
                return studentList;
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
                checkIfStudentExist(students);
            }
        }

        getStudents gs = new getStudents();
        gs.execute();
    }

    private void checkIfStudentExist(List<Student> students){
        EditText surNameEdit = findViewById(R.id.main_ChampNom);
        String surName = surNameEdit.getText().toString();
        EditText firstNameEdit = findViewById(R.id.main_ChampPrenom);
        String firstName = firstNameEdit.getText().toString();
        boolean studentFound = false;

        for(Student student : students){
            if(student.getFirstName().equalsIgnoreCase(firstName)){
                studentFound = true;
                // Création d'une intention
                Intent ChoixActivity = new Intent(this, ChoixActivity.class);

                // Lancement de la demande de changement d'activité
                startActivity(ChoixActivity);
            }
        }
        if(!studentFound){
            Toast.makeText(MainActivity.this, "Identifiants incorrects", Toast.LENGTH_LONG).show();
        }

    }
}