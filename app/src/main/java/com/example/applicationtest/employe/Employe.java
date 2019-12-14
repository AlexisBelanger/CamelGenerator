package com.example.applicationtest.employe;

import android.util.Log;

import com.example.applicationtest.GameState;

public abstract class Employe {

    protected String nom;
    protected String description;

    protected int nb;

    protected double cout;

    protected double rate;
    protected GameState gs = null;

    public Employe(String nom, String description, double coutBase, double apport) {
        this.nom = nom;
        this.description = description;
        cout = coutBase;
        this.rate = apport;
        nb = 0;
    }

    public void addOne() {

        Log.i("gs emp", gs + "");

        Log.i("employe", "add one ici ");
        Log.i("employe", "add one gs.loc : " + gs.loc);
        Log.i("employe", "add one cout : " + cout);


        if (gs.loc > cout) {
            gs.loc -= cout;
            nb++;
            cout *= 1.5;
            gs.updateValues();
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

    public void setGs(GameState gameState) {
        Log.i("gs st emp", gameState + "");
        this.gs = gameState;
        Log.i("gs st emp", this.gs + "");

    }

    public void setRate(double rate) {
        this.rate = rate;
    }


    public void setRateMult(double rate, double multiplier) {
        this.rate *= multiplier;
    }
}
