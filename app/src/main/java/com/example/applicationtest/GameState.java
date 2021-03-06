package com.example.applicationtest;


import android.graphics.Color;

import com.example.applicationtest.Amelioration.Amelioration;
import com.example.applicationtest.Amelioration.AmeliorationEffectTable;
import com.example.applicationtest.employe.Employe;

import org.json.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class GameState {


    public int new_game;
    public AcceuilActivity ac;

    public int maxIdle;


    public double totalloc;

    public double revenue_multiplier;
    public double loc;
    public double locps;


    public HashMap<String, Amelioration> ameliorations;
    public HashSet<String> taken_ameliorations;


    public double clickEfficiency;
    public double clickMult;
    public double clickbyEmp;


    public Long idleSeconds;

    public HashMap<String, Employe> employes;
    public AmeliorationEffectTable effect = new AmeliorationEffectTable();

    public int[] colors;


    public GameState(AcceuilActivity ac) {

        new_game = 0;
        loc = 0;
        locps = 0;
        maxIdle = 3600;
        revenue_multiplier = 1.0;
        this.ac = ac;
        employes = new HashMap<>();

        colors = new int[8];
        colors[0] = Color.rgb(153, 0, 0);
        colors[1] = Color.rgb(0, 0, 154);
        colors[2] = Color.rgb(0, 112, 0);
        colors[3] = Color.rgb(0, 75, 73);
        colors[4] = Color.rgb(236, 11, 250);
        colors[5] = Color.rgb(73, 0, 110);
        colors[6] = Color.rgb(51, 128, 105);
        colors[7] = Color.rgb(166, 83, 0);


        taken_ameliorations = new HashSet<>();

        try {

            JSONObject jsonObject = new JSONObject(utils.ReadFromDataFile("employes", ac.getBaseContext()));
            JSONArray jsonArray = jsonObject.getJSONArray("employes");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonEmploye = jsonArray.getJSONObject(i);
                employes.put(
                        jsonEmploye.getString("id"),
                        new Employe(
                                jsonEmploye.getString("id"),
                                jsonEmploye.getInt("rank"),
                                jsonEmploye.getString("nom"),
                                jsonEmploye.getString("description"),
                                jsonEmploye.getDouble("cout"),
                                jsonEmploye.getDouble("rate"),
                                0,
                                0,
                                (gs -> {
                                    return true;
                                }),
                                jsonEmploye.getString("src_image")
                        )
                );
            }


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        ameliorations = new HashMap<>();

        try {

            JSONObject jsonObject = new JSONObject(utils.ReadFromDataFile("ameliorations", ac.getBaseContext()));
            JSONArray jsonArray = jsonObject.getJSONArray("ameliorations");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonAmelioration = jsonArray.getJSONObject(i);
                ameliorations.put(
                        jsonAmelioration.getString("id"),
                        new Amelioration(
                                jsonAmelioration.getString("id"),
                                jsonAmelioration.getString("nom"),
                                jsonAmelioration.getString("description"),
                                jsonAmelioration.getInt("cout"),
                                effect.table.get(jsonAmelioration.getString("effect_tag")),
                                jsonAmelioration.getString("src_image")

                        )
                );


            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        clickEfficiency = 1;
        clickMult = 1;
        clickbyEmp = 0;

        idleSeconds = 0L;
    }

    public GameState(AcceuilActivity ac, String state) {
        this(ac);

        try {

            JSONObject jsonObject = new JSONObject(state);
            loc = jsonObject.getDouble("loc");
            new_game = jsonObject.getInt("ng");

            locps = jsonObject.getDouble("locps");
            clickEfficiency = jsonObject.getDouble("clickEff");
            clickMult = jsonObject.getDouble("clickMult");
            clickbyEmp = jsonObject.getDouble("clickbyEmp");

            revenue_multiplier = jsonObject.getDouble("revenue_multiplier");
            totalloc = jsonObject.getDouble("totalloc");


            for (Map.Entry<String, Employe> e : employes.entrySet()
            ) {
                e.getValue().setNb(jsonObject.getInt(e.getKey()));

                e.getValue().setTotal_production(jsonObject.getDouble(e.getKey() + "prod"));
            }

            JSONArray jsonArray = jsonObject.getJSONArray("ameliorations");


            for (int i = 0; i < jsonArray.length(); i++) {
                String amelioration = jsonArray.getString(i);

                taken_ameliorations.add(amelioration);
                ameliorations.remove(amelioration);
            }


            long saveTime = jsonObject.getLong("saveTime");
            idleSeconds = Math.min(System.currentTimeMillis() / 1000 - saveTime, maxIdle);


            addIncome(locps * idleSeconds.intValue());


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void addIncome(double income) {
        double multincome;
        if (new_game > 0) {
            multincome = income * revenue_multiplier * (new_game * 2.5);


        } else {
            multincome = income * revenue_multiplier;


        }

        loc += multincome;
        totalloc += multincome;
        ac.updateText();

        updateEmployeesProduction();

    }

    public void updateValues() {
        locps = 0;
        for (Employe e : employes.values()) {
            locps += e.getNb() * e.getRate();
        }

        ac.updateText();

    }

    public void secondTick() {

        addIncome(locps / 2);

    }

    public void updateEmployeesProduction() {
        for (Employe emp : employes.values()) {
            emp.updateTotalProduction();

        }
    }

    public void click() {
        double click_total = 0;

        for (Employe employes : employes.values()) {
            click_total += clickbyEmp * employes.getNb();
        }

        clickEfficiency = click_total + 1;

        addIncome(clickEfficiency * clickMult);
    }


    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        Long tsLong = System.currentTimeMillis() / 1000;
        try {
            jsonObject.put("ng", new_game);

            jsonObject.put("loc", loc);
            jsonObject.put("locps", locps);
            jsonObject.put("clickEff", clickEfficiency);
            jsonObject.put("clickMult", clickMult);
            jsonObject.put("clickbyEmp", clickbyEmp);

            jsonObject.put("revenue_multiplier", revenue_multiplier);
            jsonObject.put("totalloc", totalloc);


            jsonObject.put("saveTime", tsLong);

            for (Map.Entry<String, Employe> e : employes.entrySet()) {

                jsonObject.put(e.getKey(), e.getValue().getNb());
                jsonObject.put(e.getKey() + "prod", e.getValue().getTotalProduction());

            }

            JSONArray am = new JSONArray();

            for (String a : taken_ameliorations) {
                am.put(a);
            }


            jsonObject.put("ameliorations", am);

            return jsonObject.toString();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
