package com.example.applicationtest.challenge;

import androidx.arch.core.util.Function;

import com.example.applicationtest.GameState;

public class Challenge {
    protected String id;
    protected String nom;
    protected String description;
    protected int recompense;
    protected String challenge_tag;

    public Challenge(String id, String nom, String description, int recompense, String challenge_tag) {
        this.id = id;
        this.nom = nom;
        this.description = description;

        this.recompense = recompense;
        this.challenge_tag = challenge_tag;

    }

    public String getId() {
        return id;
    }

    public String getChallenge_tag() {
        return challenge_tag;
    }

    public String getDescription() {
        return description;
    }

    public String getNom() {
        return nom;
    }

    public int getRecompense() {
        return recompense;
    }
}
