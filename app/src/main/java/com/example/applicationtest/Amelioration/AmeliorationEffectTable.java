package com.example.applicationtest.Amelioration;

import androidx.arch.core.util.Function;

import com.example.applicationtest.GameState;

import java.util.HashMap;

public class AmeliorationEffectTable {

    public HashMap<String, Function<GameState, Void>> table;

    public AmeliorationEffectTable() {
        table = new HashMap<>();
        table.put("test",
                gs -> {
                    gs.addIncome(1000);
                    return null;
                });
    }


}
