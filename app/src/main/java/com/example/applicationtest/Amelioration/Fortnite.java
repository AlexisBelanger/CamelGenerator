package com.example.applicationtest.Amelioration;

import com.example.applicationtest.GameState;

public class Fortnite extends Amelioration {


    public Fortnite() {
        super("Fortnite", "A Codemaster give you more lignes of code per seconds", 1000);
    }

    @Override
    public void ChangeState(GameState state) {
        state.locps += 20;
    }


}
