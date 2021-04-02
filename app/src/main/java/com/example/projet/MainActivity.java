package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

        getStudents();
    }

    public void onChoix(View view) {
        // Création d'une intention
        Intent ChoixActivity = new Intent(this, ChoixActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(ChoixActivity);
    }

    private void getStudents() {
        ///////////////////////
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

                // Message
                Toast.makeText(MainActivity.this, "LongClick : " + students.get(0).getFirstName(), Toast.LENGTH_LONG).show();
            }
        }

        getStudents gs = new getStudents();
        gs.execute();
    }
}