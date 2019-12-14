package com.example.applicationtest;


import android.util.Log;

import com.example.applicationtest.Amelioration.Amelioration;
import com.example.applicationtest.employe.BTS;
import com.example.applicationtest.employe.Employe;
import com.example.applicationtest.employe.Stagiaire;

import org.json.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GameState {


    public double loc;
    public double locps;

    public HashMap<String, Amelioration> ameliorationMap;


    public double clickEfficiency;

    public Long idleSeconds;

    public ArrayList<Employe> employes;

    public GameState() {

        loc = 0;
        locps = 0;

        employes = new ArrayList<>();

        employes.add(new Stagiaire());
        employes.add(new BTS());


        ameliorationMap = new HashMap<>();


        clickEfficiency = 1;

        idleSeconds = 0L;
    }

    public GameState(String state) {
        this();
        try {

            JSONObject jsonObject = new JSONObject(state);
            loc = jsonObject.getDouble("loc");
//            codeurs = jsonObject.getDouble("codeurs");
//            codeursps = jsonObject.getDouble("codeursps");
            locps = jsonObject.getDouble("locps");
//            codeursEfficiency = jsonObject.getDouble("codeursEff");
            clickEfficiency = jsonObject.getDouble("clickEff");

            long saveTime = jsonObject.getLong("saveTime");
            Log.i("create saveTime", saveTime + "");

            idleSeconds = System.currentTimeMillis() / 1000 - saveTime;
            Log.i("idesecs", idleSeconds + "");

            loc += locps * idleSeconds;
//            codeurs += codeursps * idleSeconds;

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void updateValues() {
        //TODO revoir methode avec ameliorations

        locps = 0;
        for (Employe e : employes) {
            locps += e.getNb() * e.getRate();
        }

    }

    public void secondTick() {
        loc += locps;
    }

    public void click() {
        loc += clickEfficiency;
        updateValues();
    }

    public void addAmelioration(Amelioration amm) {

        if (ameliorationMap.containsKey(amm.getNom())) {
            amm = ameliorationMap.get(amm.getNom());
            amm.ChangeState(this);
            loc -= amm.getCost();
            amm.incrementCost();
            ameliorationMap.put(amm.getNom(), amm);
        } else {
            amm.ChangeState(this);
            loc -= amm.getCost();
            amm.incrementCost();
            ameliorationMap.put(amm.getNom(), amm);
        }
    }



    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        Long tsLong = System.currentTimeMillis() / 1000;
        try {
            jsonObject.put("loc", loc);
//            jsonObject.put("codeurs", codeurs);
//            jsonObject.put("codeursps", codeursps);
            jsonObject.put("locps", locps);

//            jsonObject.put("codeursEff", codeursEfficiency);
            jsonObject.put("clickEff", clickEfficiency);

            jsonObject.put("saveTime", tsLong);


            return jsonObject.toString();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
