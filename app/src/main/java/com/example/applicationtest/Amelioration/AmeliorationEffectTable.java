package com.example.applicationtest.Amelioration;

import androidx.arch.core.util.Function;

import com.example.applicationtest.GameState;

import java.util.HashMap;

public class AmeliorationEffectTable {

    public HashMap<String, Function<GameState, Void>> table;

    public AmeliorationEffectTable() {
        table = new HashMap<>();
        table.put("test_add_10k_loc",
                gs -> {
                    gs.addIncome(1000);
                    return null;
                });
        table.put("double_idle_time",
                gs -> {
                    gs.maxIdle *= 2;
                    return null;
                });
        table.put("double_multiplier",
                gs -> {
                    gs.revenue_multiplier *= 2;
                    return null;
                });
    }


}
