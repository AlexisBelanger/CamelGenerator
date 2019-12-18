package com.example.applicationtest.Amelioration;

import androidx.arch.core.util.Function;

import com.example.applicationtest.GameState;

public abstract class Amelioration {

    protected String nom;
    protected String description;
    protected int cost;


    public Amelioration(String nom, String description, int cost) {
        this.nom = nom;
        this.description = description;

        this.cost = cost;
    }


    public abstract void ChangeState(GameState state);


    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getNom() {
        return nom;
    }

}
