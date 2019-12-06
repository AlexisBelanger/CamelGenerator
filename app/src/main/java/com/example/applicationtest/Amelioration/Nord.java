package com.example.applicationtest.Amelioration;

import com.example.applicationtest.GameState;

public class Nord extends Amelioration {


    public Nord() {
        super("Nord", "A Codemaster give you more lignes of code per seconds", 1000);
    }

    @Override
    public void ChangeState(GameState state) {
        state.locps += 20;
    }
}
