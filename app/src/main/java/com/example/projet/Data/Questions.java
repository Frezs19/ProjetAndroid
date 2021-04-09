package com.example.projet.Data;
import java.util.ArrayList;
import java.util.Collections;

public class Questions {
    private ArrayList<Question> questions;

    public Questions() {
        questions = new ArrayList<>();
        questions.add(new Question("ptdr tki?","mathi","oreli","poutine",1));
        questions.add(new Question("est ceque jsuis un bouf?","mathi","oreli","poutine",1));
        questions.add(new Question("un vrai bouf??","mathi","oreli","poutine",1));
        questions.add(new Question("c vré de vré ?","mathi","oreli","poutine",1));
        Collections.shuffle(questions);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
