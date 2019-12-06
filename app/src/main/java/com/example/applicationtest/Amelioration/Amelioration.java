package com.example.applicationtest.Amelioration;

import com.example.applicationtest.GameState;

public abstract class Amelioration {

    protected String nom;
    protected String description;
    protected int base_cost;
    protected int cost;
    protected int nb_taken;


    public Amelioration(String nom, String description, int base_cost) {
        this.nom = nom;
        this.description = nom;
        this.base_cost = base_cost;

        this.nb_taken = 0;
        this.cost = base_cost;
    }


    public void incrementCost() {
        nb_taken++;
        cost = (int) (base_cost * Math.exp(nb_taken + 1));
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

    public int getBase_cost() {
        return base_cost;
    }

    public int getNb_taken() {
        return nb_taken;
    }
}
