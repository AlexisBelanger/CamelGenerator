package com.example.applicationtest.Amelioration;

import androidx.arch.core.util.Function;

import com.example.applicationtest.GameState;

import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSValue;

import java.text.DecimalFormat;


public class Amelioration {
    protected String id;
    protected String nom;
    protected String description;
    protected int cost;
    protected Function<GameState, Void> effect;


    public Amelioration(String id, String nom, String description, int cost, Function<GameState, Void> effect) {
        this.id = id;
        this.nom = nom;
        this.description = description;

        this.cost = cost;
        this.effect = effect;

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

}
