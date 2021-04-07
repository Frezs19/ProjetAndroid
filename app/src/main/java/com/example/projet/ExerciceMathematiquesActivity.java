package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projet.Data.Operation;

import java.util.ArrayList;
import java.util.Random;

public class ExerciceMathematiquesActivity extends AppCompatActivity {

    public static final String OP1_KEY = "op1_key";
    public static final String OP2_KEY = "op2_key";
    public static final String OPERATION_KEY = "operation_key";
    public static final String TIMER_KEY = "timer_key";
    public static final String NBCALCULS_KEY = "nbcalcul_key";

    private Operation addition;
    private static final Random random = new Random();
    private ArrayList<LinearLayout> listeAddition = new ArrayList<>();
    private ArrayList<EditText> listeReponses = new ArrayList<>();
    private ArrayList<Operation> listeCalcul = new ArrayList<>();
    private String operation;
    private EditText resultat;
    private TextView calcul;
    private LinearLayout linear;
    CountDownTimer Chrono;
    int temps = 0; //Compteur pour le mode timer si l'utilisateur a fini ses calculs avant la fin du timer
    boolean timer = false;
    ExerciceMathematiquesActivity vue = this; //Pour que le timer lance un intent
    int nbErreur = 0;
    int nbAdditions = 10; //Valeur de base -> 10, mais on la change par la suite lors de la récupération des informations passés à la vue
    int numAddition = 1;
    int nbCalculBons = 0;
    TextView compteurAddition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_mathematiques);

        //Récupération du nombre de calcul
        nbAdditions = getIntent().getIntExtra(NBCALCULS_KEY, 10);

        compteurAddition = findViewById(R.id.compteurAddition);
        compteurAddition.setText(numAddition + "/" + nbAdditions);

        //Récupération du mode
        timer = getIntent().getBooleanExtra(TIMER_KEY, false);
        if (timer) {
            //Création du timer et de ses méthodes
            Chrono = new CountDownTimer(60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //Compteur addition devient un compteur de secondes
                    compteurAddition.setText("Il reste : " + millisUntilFinished / 1000 + " secondes");
                    temps++;
                }

                public void onFinish() {
                    Intent intent = new Intent(vue, FinModeTimer.class);
                    intent.putExtra(FinModeTimer.NBREUSSI_KEY, nbCalculBons);
                    startActivity(intent);
                }
            }.start();
        }

        //Récupération du choix des centaines/dizaines
        int op1 = getIntent().getIntExtra(OP1_KEY, 9);
        int op2 = getIntent().getIntExtra(OP2_KEY, 9);

        //Récupération de l'opération
        String op = getIntent().getStringExtra(OPERATION_KEY);
        operation = op;

        for (int i=0; i < nbAdditions; i++) {
            switch (operation) {
                case " + ":
                    addition = new Operation(random.nextInt(op1), random.nextInt(op2));
                    break;
                case " - ": //On veut que l'op2 soit plus petit que l'op1 pour que le résultat soit toujours positif
                    int Sv1 = random.nextInt(op1);
                    int Sv2 = random.nextInt(op2);
                    while (Sv2 > Sv1) {
                        Sv2 = random.nextInt(op2);
                    }
                    addition = new Operation(Sv1, Sv2);
                    break;
                case " x ":
                    addition = new Operation(random.nextInt(op1), random.nextInt(op2));
                    break;
                case " / ": //On veut que des nombres multiples entre eux et que op2 soit inférieur à l'op1 pour avoir un résultat entier
                    int Dv1 = random.nextInt(op1);
                    int Dv2 = random.nextInt(op2);
                    while ( (Dv2==0) || (Dv2>Dv1) || (Dv1%Dv2!=0) ) {
                        Dv2 = random.nextInt(op2);
                    }
                    addition = new Operation(Dv1, Dv2);
                    break;
            }

            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(addition.getOperande1() + operation + addition.getOperande2() + " = ");

            resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);

            listeAddition.add(linearTMP);
            listeCalcul.add(addition);
            listeReponses.add(resultat);
        }
        linear = findViewById(R.id.linear);
        linear.addView(listeAddition.get(numAddition-1));
    }

    public void onResult(View view) {
        //Si on est en mode timer, on vérifie le calcul pour savoir si on affiche ou pas le calcul suivant
        if (timer) {
            if (listeReponses.get(nbCalculBons).getText().toString().compareTo("")==0) {
                nbErreur++;
            } else {
                listeCalcul.get(nbCalculBons).setRepUser(Integer.parseInt(listeReponses.get(nbCalculBons).getText().toString()));
                switch (operation) {
                    case " + ":
                        if (!listeCalcul.get(nbCalculBons).resultatAddition()) {
                            nbErreur++;
                        }
                        break;
                    case " - ":
                        if (!listeCalcul.get(nbCalculBons).resultatSoustraction()) {
                            nbErreur++;
                        }
                        break;
                    case " x ":
                        if (!listeCalcul.get(nbCalculBons).resultatMultiplication()) {
                            nbErreur++;
                        }
                        break;
                    case " / ":
                        if (!listeCalcul.get(nbCalculBons).resultatDivision()) {
                            nbErreur++;
                        }
                        break;
                }
            }
            //Si l'utilisateur s'est trompée (nbErreur!=0)
            if (nbErreur!=0) {
                //On remet le compteur à zéro pour les prochaines vérifications (on pourrait utiliser un boolean mais la variable nbErreur existait déjà)
                nbErreur=0;
                //On lui signal qu'il a faux avec le texte en rouge
                listeReponses.get(nbCalculBons).setTextColor(0xffeb4f34);
            //Si l'utilisateur a eu bon, on passe au calcul suivant
            } else {
                //On utilise nbCalculBons pour compter le nombre de calcul réussis
                nbCalculBons++;
                //On affiche le calcul suivant si il en reste un
                if (numAddition!=nbAdditions) {
                    linear.removeAllViews();
                    numAddition++;
                    linear.addView(listeAddition.get(numAddition-1));
                } else {
                    Chrono.cancel();
                    Intent intent = new Intent(vue, FinModeTimer.class);
                    intent.putExtra(FinModeTimer.NBREUSSI_KEY, nbCalculBons);
                    intent.putExtra(FinModeTimer.TIME_KEY, temps);
                    startActivity(intent);
                }
            }
        } else {
            //On fait la façon "classique"
            //Si il a encore des additions à afficher
            if (numAddition!=nbAdditions) {
                linear.removeAllViews();
                numAddition++;
                compteurAddition = findViewById(R.id.compteurAddition);
                compteurAddition.setText(numAddition + "/" + nbAdditions);
                linear.addView(listeAddition.get(numAddition-1));
            } else {
                //Vérifie les erreurs
                nbErreur=0;
                int i=0;
                for (Operation addition : listeCalcul) {
                    if (listeReponses.get(i).getText().toString().compareTo("")==0) {
                        nbErreur++;
                    } else {
                        addition.setRepUser(Integer.parseInt(listeReponses.get(i).getText().toString()));
                        switch (operation) {
                            case " + ":
                                if (!addition.resultatAddition()) {
                                    nbErreur++;
                                }
                                break;
                            case " - ":
                                if (!addition.resultatSoustraction()) {
                                    nbErreur++;
                                }
                                break;
                            case " x ":
                                if (!addition.resultatMultiplication()) {
                                    nbErreur++;
                                }
                                break;
                            case " / ":
                                if (!addition.resultatDivision()) {
                                    nbErreur++;
                                }
                                break;
                        }
                    }
                    i++;
                }
                //Sinon on regarde le nombre d'erreur et on affiche la bonne activite
                if (nbErreur == 0) {
                    Intent intent = new Intent(this, FelicitationActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, ErreurActivity.class);
                    intent.putExtra(ErreurActivity.ERREUR_KEY, nbErreur);
                    startActivity(intent);
                }
            }
        }
    }

    public void onPrec(View view) {
        if (numAddition!=1) {
            linear.removeAllViews();
            numAddition--;
            compteurAddition = findViewById(R.id.compteurAddition);
            compteurAddition.setText(numAddition + "/10");
            linear.addView(listeAddition.get(numAddition-1));
        }
    }
}