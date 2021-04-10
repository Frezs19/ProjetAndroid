package com.example.projet.Data;
import java.util.ArrayList;
import java.util.Collections;

public class Questions {
    private ArrayList<Question> questions;

    public Questions() {
        questions = new ArrayList<>();
        questions.add(new Question("Les mots «pair» et «paire» sont des...","Des antonymes","Des homonymes","Des synonymes",2));
        questions.add(new Question("Combien un cube a-t-il de face ?","4","6","8",2));
        questions.add(new Question("Quel océan entoure l'île de Madagascar ?","Océan Pacifique","Océan Atlantique","Océan Indien",3));
        questions.add(new Question("Lequel de ces pays n'appartient pas à l'Union européenne ?","Suisse","Belgique","Grèce",1));
        questions.add(new Question("Qui est le président de la France en 2018 ?","Nicolas Sarkozy","François Hollande","Emmanuel Macron",2));
        questions.add(new Question("Quelle est la langue officielle des Pays-Bas ?","L'allemand","Le français","Le néerlandais",3));
        questions.add(new Question("Quelle est la bonne orthographe ?","Amendement","Amandemant","Amandement",1));
        questions.add(new Question("Pour tracer un angle droit, j'utilise ...","L'équerre","Le compas","La règle",1));
        questions.add(new Question("Complète le bon verbe : tu ... .","Grandis","Dansez","Avons",1));
        questions.add(new Question("What is your name ?","Mi name ...","My names ...","My name is ...",3));
        Collections.shuffle(questions);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
