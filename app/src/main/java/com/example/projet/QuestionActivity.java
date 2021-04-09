package com.example.projet;

import android.content.Intent;
import android.os.Bundle;

import com.example.projet.Data.Question;
import com.example.projet.Data.Questions;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {
    private Questions questions;
    private int numeroQuestionActuelle = 0;
    private int nbErreur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView question = findViewById(R.id.question_Question);
        Button reponse1 = findViewById(R.id.question_bouton1);
        Button reponse2 = findViewById(R.id.question_bouton2);
        Button reponse3 = findViewById(R.id.question_bouton3);

        questions = new Questions();

        afficherQuestionActuelle();
        actualiserAvancement();

        reponse1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cliqueBouton(1);
            }
        });

        reponse2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cliqueBouton(2);
            }
        });

        reponse3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cliqueBouton(3);
            }
        });
    }

    private void cliqueBouton(int numeroBouton){
        String reponse;
        if(verifierResultat(numeroBouton)){
            reponse = "correct";
        } else {
            reponse = "incorrect";
        }
        Toast.makeText(QuestionActivity.this, "Réponse " + reponse, Toast.LENGTH_SHORT).show();
        afficherQuestionSuivante();
        actualiserAvancement();
    }

    private void afficherQuestionActuelle(){
        TextView question = findViewById(R.id.question_Question);
        Button reponse1 = findViewById(R.id.question_bouton1);
        Button reponse2 = findViewById(R.id.question_bouton2);
        Button reponse3 = findViewById(R.id.question_bouton3);

        Question questionActuelle = questions.getQuestions().get(numeroQuestionActuelle);
        question.setText(questionActuelle.getQuestion());
        reponse1.setText(questionActuelle.getReponse1());
        reponse2.setText(questionActuelle.getReponse2());
        reponse3.setText(questionActuelle.getReponse3());
    }

    private boolean verifierResultat(int reponseDonne){
        if(questions.getQuestions().get(numeroQuestionActuelle).getBonneReponseIndex() == reponseDonne){
            return true;
        }
        else {
            nbErreur++;
            return false;
        }
    }

    private void afficherQuestionSuivante(){
        if(numeroQuestionActuelle + 1 < questions.getQuestions().size()){
            numeroQuestionActuelle++;
            afficherQuestionActuelle();
        }
        else {
            if (nbErreur == 0) {
                Intent intentFelicitation = new Intent(this, FelicitationActivity.class);
                startActivity(intentFelicitation);
            } else {
                Intent intentErreur = new Intent(this, ErreurActivity.class);
                intentErreur.putExtra(ErreurActivity.ERREUR_KEY, nbErreur);
                startActivity(intentErreur);
            }
        }
    }

    private void actualiserAvancement(){
        TextView avancement = findViewById(R.id.question_Avancement);
        avancement.setText(numeroQuestionActuelle+1 + "/" + questions.getQuestions().size());
    }
}