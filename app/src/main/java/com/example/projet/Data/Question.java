package com.example.projet.Data;

public class Question {
    private String question;
    private String reponse1;
    private String reponse2;
    private String reponse3;
    private Integer bonneReponseIndex;

    public Question(String question, String reponse1, String reponse2, String reponse3, Integer bonneReponseIndex) {
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.bonneReponseIndex = bonneReponseIndex;
    }

    public Integer getBonneReponseIndex() {
        return bonneReponseIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String getReponse1() {
        return reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }
}
