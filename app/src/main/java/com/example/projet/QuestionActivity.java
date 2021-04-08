package com.example.projet;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.projet.Data.Questions;
import com.example.projet.db.DatabaseClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

public class QuestionActivity extends AppCompatActivity {
    private Questions questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        try {
            questions = new Questions();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView question = findViewById(R.id.question_Question);
        question.setText("ouiii");
    }
}