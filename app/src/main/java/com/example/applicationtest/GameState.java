package com.example.applicationtest;


import android.util.Log;

import com.example.applicationtest.Amelioration.Amelioration;
import com.example.applicationtest.employe.Employe;

import org.json.*;

import java.util.HashMap;
import java.util.Map;


public class GameState {


    public AcceuilActivity ac;

    public int maxIdle;


    public double totalloc;

    public double revenue_multiplier;
    public double loc;
    public double locps;

    public HashMap<String, Amelioration> ameliorations;


    public double clickEfficiency;

    public Long idleSeconds;

    public HashMap<String, Employe> employes;
    public HashMap<String, Employe> availebleEmployes;


    public GameState(AcceuilActivity ac) {

        loc = 0;
        locps = 0;
        maxIdle = 3600;
        revenue_multiplier = 1.0;
        this.ac = ac;
        employes = new HashMap<>();
        availebleEmployes = new HashMap<>();


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
        ameliorations.put("test", new Amelioration("TEST", "TEST", 10, gs -> {
            gs.addIncome(1000);
            return null;
        }));

//        ameliorations.put("ahah", new Amelioration("TEST", "TEST", 10, "gs -> {gs.addIncome(1000);return null;}" ));



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

            for (Map.Entry<String, Employe> e : employes.entrySet()
            ) {
                e.getValue().setNb(jsonObject.getInt(e.getKey()));
            }

            long saveTime = jsonObject.getLong("saveTime");
            Log.i("create saveTime", saveTime + "");

            idleSeconds = Math.min(System.currentTimeMillis() / 1000 - saveTime, maxIdle);

            Log.i("idesecs", idleSeconds + "");

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
    }

    public void updateValues() {
        locps = 0;
        for (Employe e : employes.values()) {
            locps += e.getNb() * e.getRate();
        }

        Log.i("GameState", "locps end : " + locps);

    }

    public void secondTick() {

        addIncome(locps);
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
            jsonObject.put("saveTime", tsLong);

            for (Map.Entry<String, Employe> e : employes.entrySet()
            ) {
                jsonObject.put(e.getKey(), e.getValue().getNb());


            }

            return jsonObject.toString();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
