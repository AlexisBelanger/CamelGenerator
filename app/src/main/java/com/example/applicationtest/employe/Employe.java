package com.example.applicationtest.employe;

import com.example.applicationtest.GameState;

public abstract class Employe {

    protected String nom;
    protected String description;

    protected int nb;

    protected double cout;

    protected double rate;
    protected GameState gs;

    public Employe(String nom, String description, double coutBase, double apport, GameState gameState) {
        this.nom = nom;
        this.description = description;
        cout = coutBase;
        this.rate = apport;
        gs = gameState;
        nb = 0;
    }

    public void addOne() {
        if (gs.loc >= cout) {
            gs.loc -= cout;
            nb++;
            cout *= 1.5;
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

    public void setRate(double rate) {
        this.rate = rate;
    }


    public void setRateMult(double rate, double multiplier) {
        this.rate *= multiplier;
    }
}
