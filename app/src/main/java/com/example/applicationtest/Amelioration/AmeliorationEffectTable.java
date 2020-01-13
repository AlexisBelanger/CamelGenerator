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
        table.put("double_bts_prod",
                gs -> {
                    gs.employes.get("bts").setRate(gs.employes.get("bts").getRate() * 2);
                    return null;
                });

        table.put("double_stage_prod",
                gs -> {
                    gs.employes.get("stagiaire").setRate(gs.employes.get("stagiaire").getRate() * 2);
                    return null;
                });
        table.put("double_master_prod",
                gs -> {
                    gs.employes.get("master").setRate(gs.employes.get("master").getRate() * 2);
                    return null;
                });

        table.put("double_scrum_prod",
                gs -> {
                    gs.employes.get("scrum-master").setRate(gs.employes.get("scrum-master").getRate() * 2);
                    return null;
                });

        table.put("en_fait_justement",
                gs -> {
                    gs.employes.get("product").setRate(gs.employes.get("product").getRate() * 2);
                    gs.employes.get("stagiaire").setRate(gs.employes.get("stagiaire").getRate() * 0.5);
                    return null;
                });

        table.put("reponse",
                gs -> {
                    gs.employes.get("stagiaire").setRate(gs.employes.get("stagiaire").getRate() * (1 + gs.employes.get("product").getNb() * 0.05));
                    return null;
                });

        table.put("cours_pc2r",
                gs -> {
                    gs.employes.get("master").setRate(gs.employes.get("master").getRate() * (1 + gs.employes.get("chailloux").getNb() * 0.05));
                    return null;
                });

        table.put("double_tezos_prod",
                gs -> {
                    gs.employes.get("tezos").setRate(gs.employes.get("tezos").getRate() * 2);
                    return null;
                });

        table.put("up_chailloux_prod",
                gs -> {
                    gs.employes.get("chailloux").setRate(gs.employes.get("chailloux").getRate() * 1.5);
                    return null;
                });

        table.put("double_inria_prod",
                gs -> {
                    gs.employes.get("inria").setRate(gs.employes.get("inria").getRate() * 2);
                    return null;
                });
        table.put("double_click",
                gs -> {
                    gs.clickMult *= 2;
                    return null;
                });

        table.put("clickç_0.5",
                gs -> {
                    gs.clickbyEmp += 0.5;
                    return null;
                });

        table.put("clickç_1",
                gs -> {
                    gs.clickbyEmp += 1;
                    return null;
                });

        table.put("clickç_5",
                gs -> {
                    gs.clickbyEmp += 5;
                    return null;
                });

        table.put("clickç_50",
                gs -> {
                    gs.clickbyEmp += 50;
                    return null;
                });

        table.put("clickç_500",
                gs -> {
                    gs.clickbyEmp += 500;
                    return null;
                });
    }


}
