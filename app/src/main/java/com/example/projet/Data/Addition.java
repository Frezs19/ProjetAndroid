package com.example.projet.Data;

public class Addition {

    private int op1;
    private int op2;
    private Integer repUser;

    public Addition(int op1, int op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    public int getOperande1() { return this.op1;}

    public void setOperande1(int valeur) { this.op1 = valeur;}

    public int getOperande2() { return this.op2;}

    public void setOperande2(int valeur) { this.op2 = valeur;}

    public void setRepUser(Integer valeur) { this.repUser = valeur;}

    public boolean resultatAddition() {
        return (op1+op2) == repUser;
    }

}
