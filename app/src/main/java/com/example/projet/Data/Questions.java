package com.example.projet.Data;
import org.json.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Questions {
    private ArrayList<Question> questions;

    public Questions() throws JSONException {
        String jsonString = "" ;

        //On lit le fichier JSON
        try {
            File myObj = new File("questions.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                jsonString += myReader.next();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        JSONObject jsonObj = new JSONObject(jsonString);

        //On parcours les questions du JSON et on les ajoute a l'attribut de l'objet
        JSONArray questions = jsonObj.getJSONArray("questions");
        for (int i = 0; i < questions.length(); i++)
        {
            String question = questions.getJSONObject(i).getString("question");
            String reponse1 = questions.getJSONObject(i).getString("reponse1");
            String reponse2 = questions.getJSONObject(i).getString("reponse2");
            String reponse3 = questions.getJSONObject(i).getString("reponse3");
            String bonneReponseIndex = questions.getJSONObject(i).getString("bonneReponseIndex");
            questions.put(new Question(question, reponse1, reponse2, reponse3, Integer.parseInt(bonneReponseIndex)));
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
