package com.example.applicationtest.Amelioration;

import androidx.arch.core.util.Function;

import com.example.applicationtest.GameState;

public class Amelioration {
    protected String id;
    protected String nom;
    protected String description;
    protected int cost;
    protected Function<GameState, Void> effect;
    protected String src_image;




    public Amelioration(String id, String nom, String description, int cost, Function<GameState, Void> effect, String src_image) {
        this.id = id;
        this.nom = nom;
        this.description = description;

        this.cost = cost;
        this.effect = effect;
        this.src_image = src_image;

    }


    public void ChangeState(GameState state) {
        effect.apply(state);
    }


    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getNom() {
        return nom;
    }

    public String getSrc_image() {
        return src_image;
    }

}
