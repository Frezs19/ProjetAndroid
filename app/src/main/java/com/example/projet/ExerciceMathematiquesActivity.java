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

    private Operation operation;
    private static final Random random = new Random();
    private ArrayList<LinearLayout> listeOperation = new ArrayList<>();
    private ArrayList<EditText> listeReponses = new ArrayList<>();
    private ArrayList<Operation> listeCalcul = new ArrayList<>();
    private String operationString;
    private EditText resultat;
    private TextView calcul;
    private LinearLayout linear;
    CountDownTimer Chrono;
    int temps = 0; //Compteur pour le mode timer si l'utilisateur a fini ses calculs avant la fin du timer
    boolean timer = false;
    ExerciceMathematiquesActivity vue = this; //Pour que le timer lance un intent
    int nbErreur = 0;
    int nbOperation = 10; //Valeur de base -> 10, mais on la change par la suite lors de la récupération des informations passés à la vue
    int numOperation = 1;
    int nbCalculBons = 0;
    TextView compteurOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_mathematiques);

        //Récupération du nombre de calcul
        nbOperation = getIntent().getIntExtra(NBCALCULS_KEY, 10);

        //Récupération et mise à jour du compteur d'opérations
        compteurOperation = findViewById(R.id.exoMaths_compteurOperation);
        compteurOperation.setText(numOperation + "/" + nbOperation);

        //Récupération du mode timer/pas timer
        timer = getIntent().getBooleanExtra(TIMER_KEY, false);
        if (timer) {
            //Création du timer et de ses méthodes
            Chrono = new CountDownTimer(60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //Compteur addition devient un compteur de secondes
                    compteurOperation.setText("Il reste : " + millisUntilFinished / 1000 + " secondes");
                    temps++; //Incrémentation de la variable temps pour le cas ou l'utilisateur finirai ses calculs avant la fin du timer
                }

                public void onFinish() {
                    Intent intentFinModeTimer = new Intent(vue, FinModeTimer.class);
                    intentFinModeTimer.putExtra(FinModeTimer.NBREUSSI_KEY, nbCalculBons);
                    startActivity(intentFinModeTimer);
                }
            }.start();
        }

        //Récupération du choix des centaines/dizaines
        int op1 = getIntent().getIntExtra(OP1_KEY, 9);
        int op2 = getIntent().getIntExtra(OP2_KEY, 9);

        //Récupération de l'opération
        String op = getIntent().getStringExtra(OPERATION_KEY);
        operationString = op;

        //Création des calculs, des linears
        for (int i=0; i < nbOperation; i++) {
            switch (operationString) {
                case " + ":
                    operation = new Operation(random.nextInt(op1), random.nextInt(op2));
                    break;
                case " - ": //On veut que l'op2 soit plus petit que l'op1 pour que le résultat soit toujours positif
                    int Sv1 = random.nextInt(op1);
                    int Sv2 = random.nextInt(op2);
                    while (Sv2 > Sv1) {
                        Sv2 = random.nextInt(op2);
                    }
                    operation = new Operation(Sv1, Sv2);
                    break;
                case " x ":
                    operation = new Operation(random.nextInt(op1), random.nextInt(op2));
                    break;
                case " / ": //On veut unqiuement des nombres multiples entre eux et que op2 soit inférieur à l'op1 pour avoir un résultat entier
                    int Dv1 = random.nextInt(op1/2);
                    if (Dv1==0) {
                        Dv1 = 2;
                    }
                    if (Dv1%2!=0) {
                        Dv1 = Dv1*2;
                    }
                    int Dv2 = random.nextInt(op2/2);
                    if (Dv2==0) {
                       Dv2 = 2;
                    }
                    if (Dv2>Dv1) {
                        Dv2 = Dv1;
                    }
                    operation = new Operation(Dv1, Dv2);
                    break;
            }

            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(operation.getOperande1() + operationString + operation.getOperande2() + " = ");

            resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);

            listeOperation.add(linearTMP);
            //On stock dans une liste, les calculs et les résponses
            listeCalcul.add(operation);
            listeReponses.add(resultat);
        }
        //On affiche le premier calcul
        linear = findViewById(R.id.linear);
        linear.addView(listeOperation.get(numOperation-1));
    }

    public void onResult(View view) {
        //Si on est en mode timer, on vérifie le calcul pour savoir si on affiche ou pas le calcul suivant
        if (timer) {
            if (listeReponses.get(nbCalculBons).getText().toString().compareTo("")==0) {
                nbErreur++;
            } else {
                listeCalcul.get(nbCalculBons).setRepUser(Integer.parseInt(listeReponses.get(nbCalculBons).getText().toString()));
                switch (operationString) {
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
                if (numOperation!=nbOperation) {
                    linear.removeAllViews();
                    numOperation++;
                    linear.addView(listeOperation.get(numOperation-1));
                } else {
                    //Ici l'utilisateur a fini ses calculs avant la fin du timer donc on stop le timer et on affiche l'activité FinModeTimer avec le temps qu'il a fait et le nb de calcul
                    Chrono.cancel();
                    Intent intentFinModeTimer = new Intent(vue, FinModeTimer.class);
                    intentFinModeTimer.putExtra(FinModeTimer.NBREUSSI_KEY, nbCalculBons);
                    intentFinModeTimer.putExtra(FinModeTimer.TIME_KEY, temps);
                    startActivity(intentFinModeTimer);
                }
            }
        } else {
            //On fait la façon "classique"
            //Si il a encore des additions à afficher
            if (numOperation!=nbOperation) {
                linear.removeAllViews();
                numOperation++;
                compteurOperation = findViewById(R.id.exoMaths_compteurOperation);
                compteurOperation.setText(numOperation + "/" + nbOperation);
                linear.addView(listeOperation.get(numOperation-1));
            } else {
                //Vérifie les erreurs
                nbErreur=0;
                int i=0;
                for (Operation addition : listeCalcul) {
                    if (listeReponses.get(i).getText().toString().compareTo("")==0) {
                        nbErreur++;
                    } else {
                        addition.setRepUser(Integer.parseInt(listeReponses.get(i).getText().toString()));
                        switch (operationString) {
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
                    Intent intentFelicitation = new Intent(this, FelicitationActivity.class);
                    startActivity(intentFelicitation);
                } else {
                    Intent intentErreur = new Intent(this, ErreurActivity.class);
                    intentErreur.putExtra(ErreurActivity.ERREUR_KEY, nbErreur);
                    startActivity(intentErreur);
                }
            }
        }
    }

    public void onPrec(View view) {
        //Le bouton précédent ne fonctionne que en mode non timer (on aurait pu le cacher mais c'est plus compliqué)
        if ( (numOperation!=1) && (!timer)) {
            linear.removeAllViews();
            numOperation--;
            compteurOperation = findViewById(R.id.exoMaths_compteurOperation);
            compteurOperation.setText(numOperation + "/" + nbOperation);
            linear.addView(listeOperation.get(numOperation-1));
        }
    }
}