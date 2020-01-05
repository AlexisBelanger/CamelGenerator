package com.example.applicationtest;


import android.graphics.Color;
import android.util.Log;

import com.example.applicationtest.Amelioration.Amelioration;
import com.example.applicationtest.Amelioration.AmeliorationEffectTable;
import com.example.applicationtest.employe.Employe;
import com.example.applicationtest.ui.home.HomeFragment;

import org.json.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class GameState {


    public AcceuilActivity ac;

    public int maxIdle;


    public double totalloc;

    public double revenue_multiplier;
    public double loc;
    public double locps;


    public HashMap<String, Amelioration> ameliorations;
    public HashSet<String> taken_ameliorations;


    public double clickEfficiency;

    public Long idleSeconds;

    public HashMap<String, Employe> employes;
    public AmeliorationEffectTable effect = new AmeliorationEffectTable();

    public int[] colors;


    public GameState(AcceuilActivity ac) {

        loc = 0;
        locps = 0;
        maxIdle = 3600;
        revenue_multiplier = 1.0;
        this.ac = ac;
        employes = new HashMap<>();

        colors = new int[8];
        colors[0] = Color.RED;
        colors[1] = Color.BLUE;
        colors[2] = Color.GREEN;
        colors[3] = Color.YELLOW;
        colors[4] = Color.rgb(236,11,250);
        colors[5] = Color.CYAN;
        colors[6] = Color.rgb(250,150,0);
        colors[7] = Color.rgb(143,0,226);


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
                                })
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
                                effect.table.get(jsonAmelioration.getString("effect_tag"))

                        )
                );


            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        clickEfficiency = 1;

        idleSeconds = 0L;
    }

    public GameState(AcceuilActivity ac, String state) {
        this(ac);

        try {

            JSONObject jsonObject = new JSONObject(state);
            loc = jsonObject.getDouble("loc");
            locps = jsonObject.getDouble("locps");
            clickEfficiency = jsonObject.getDouble("clickEff");
            revenue_multiplier = jsonObject.getDouble("revenue_multiplier");
            totalloc = jsonObject.getDouble("totalloc");

            for (Map.Entry<String, Employe> e : employes.entrySet()
            ) {
                e.getValue().setNb(jsonObject.getInt(e.getKey()));
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
        double multincome = income * revenue_multiplier;

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

        addIncome(locps);

    }

    public void updateEmployeesProduction(){
        for(Employe emp : employes.values()){
            emp.updateTotalProduction();

        }
    }

    public void click() {
        addIncome(clickEfficiency);
    }

    public void addAmelioration(Amelioration amm) {


    }


    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        Long tsLong = System.currentTimeMillis() / 1000;
        try {
            jsonObject.put("loc", loc);
            jsonObject.put("locps", locps);
            jsonObject.put("clickEff", clickEfficiency);
            jsonObject.put("revenue_multiplier", revenue_multiplier);
            jsonObject.put("totalloc", totalloc);


            jsonObject.put("saveTime", tsLong);

            for (Map.Entry<String, Employe> e : employes.entrySet()) {
                jsonObject.put(e.getKey(), e.getValue().getNb());
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
