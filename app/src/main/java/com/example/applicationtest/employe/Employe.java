package com.example.applicationtest.employe;

import android.util.Log;

import com.example.applicationtest.GameState;

public abstract class Employe {

    protected String nom;
    protected String description;

    protected int nb;


    protected double coutBase;
    protected double cout;

    protected double rate;
    protected GameState gs;

    public Employe(String nom, String description, double coutBase, double apport, int nb, GameState gs) {
        this.nom = nom;
        this.description = description;
        this.coutBase = coutBase;
        this.cout = (int) coutBase * Math.pow(1.15, nb);
        this.rate = apport;
        this.nb = nb;
        this.gs = gs;
    }

    public void addOne(GameState gs) {

        if (gs.loc > cout) {
            gs.loc -= cout;
            this.nb++;
            cout = (int) coutBase * Math.pow(1.15, nb);
            for (Employe e : gs.employes) {
                if (e.nom.equals(this.nom)) {
                    e.setNb(this.nb);
                }
            }
            gs.updateValues();

            Log.i("Employe", "addOne: ");
            Log.i("Employe", "nb : " + nb);
            Log.i("Employe", "cout : " + cout);
        }
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public double getCout() {
        return cout;
    }

    public double getRate() {
        return rate;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public void setGs(GameState gs) {
        this.gs = gs;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


    public void setRateMult(double rate, double multiplier) {
        this.rate *= multiplier;
    }
}
