package com.example.projet.Data;

public class Operation {

    private int op1;
    private int op2;
    private Integer repUser;

    public Operation(int op1, int op2) {
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

    public boolean resultatSoustraction() {
        return (op1-op2) == repUser;
    }

    public boolean resultatMultiplication() {
        return (op1*op2) == repUser;
    }

    public boolean resultatDivision() {
        return (op1/op2) == repUser;
    }

}
