package com.example.applicationtest.employe;

import android.util.Log;

import androidx.arch.core.util.Function;

import com.example.applicationtest.GameState;

import java.util.concurrent.locks.Condition;

public class Employe {
    protected String id;
    protected int rank;

    protected String nom;
    protected String description;

    protected int nb;


    protected double coutBase;
    protected double cout;

    protected double rate;
    protected double total_rate;


    protected Function<GameState, Boolean> condition;


    public Employe(String id, int rank, String nom, String description, double coutBase, double apport, int nb, Function<GameState, Boolean> condition) {
        this.id = id;
        this.rank = rank;
        this.nom = nom;

        this.description = description;
        this.coutBase = coutBase;
        this.cout = (int) (coutBase * Math.pow(1.15, nb));
        this.rate = apport;
        this.total_rate = rate * nb;
        this.nb = nb;
        this.condition = condition;

    }

    public void addOne(GameState gs) {

        if (gs.loc >= cout) {
            gs.loc -= cout;
            this.nb++;
            cout = (int) (coutBase * Math.pow(1.15, nb));
            this.total_rate = rate * nb;


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

    public int getRank() {
        return rank;
    }

    public void setNb(int nb) {
        this.nb = nb;
        cout = (int) (coutBase * Math.pow(1.15, nb));
        this.total_rate = rate * nb;

    }


    public void setRate(double rate) {
        this.rate = rate;
    }

    public Function<GameState, Boolean> getCondition() {
        return condition;
    }

    public void setRateMult(double rate, double multiplier) {
        this.rate *= multiplier;
    }
}
