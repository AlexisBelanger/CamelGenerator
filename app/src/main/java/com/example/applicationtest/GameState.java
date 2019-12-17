package com.example.applicationtest;


import android.util.Log;

import com.example.applicationtest.Amelioration.Amelioration;
import com.example.applicationtest.employe.BTS;
import com.example.applicationtest.employe.Employe;
import com.example.applicationtest.employe.Master;
import com.example.applicationtest.employe.ProductOwner;
import com.example.applicationtest.employe.ScrumMaster;
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

        employes.add(new Stagiaire(this, 0));
        employes.add(new BTS(this, 0));

        employes.add(new Master(this, 0));
        employes.add(new ScrumMaster(this, 0));
        employes.add(new ProductOwner(this, 0));

        ameliorationMap = new HashMap<>();


        clickEfficiency = 1;

        idleSeconds = 0L;
    }

    public GameState(String state) {
        this();

        try {

            JSONObject jsonObject = new JSONObject(state);
            loc = jsonObject.getDouble("loc");
            locps = jsonObject.getDouble("locps");
            clickEfficiency = jsonObject.getDouble("clickEff");
            employes.set(0, new Stagiaire(this, jsonObject.getInt("stagiaire")));
            employes.set(1, new BTS(this, jsonObject.getInt("bts")));
            employes.set(2, new Master(this, jsonObject.getInt("master")));
            employes.set(3, new ScrumMaster(this, jsonObject.getInt("scrum")));
            employes.set(4, new ProductOwner(this, jsonObject.getInt("productOwner")));

            long saveTime = jsonObject.getLong("saveTime");
            Log.i("create saveTime", saveTime + "");

            idleSeconds = System.currentTimeMillis() / 1000 - saveTime;
            Log.i("idesecs", idleSeconds + "");

            loc += locps * idleSeconds;

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void updateValues() {
        Log.i("GameState", "updateValues: ");
        Log.i("GameState", "loc: " + loc);
        Log.i("GameState", "locps start : " + locps);

        locps = 0;
        for (Employe e : employes) {

            Log.i("GameState", "locps: " + locps);
            Log.i("GameState", "e.nb: " + e.getNb());
            Log.i("GameState", "e.rate: " + e.getRate());

            locps += e.getNb() * e.getRate();
        }


        Log.i("GameState", "locps end : " + locps);

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
            ameliorationMap.put(amm.getNom(), amm);
        } else {
            amm.ChangeState(this);
            loc -= amm.getCost();
            ameliorationMap.put(amm.getNom(), amm);
        }
    }


    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        Long tsLong = System.currentTimeMillis() / 1000;
        try {
            jsonObject.put("loc", loc);
            jsonObject.put("locps", locps);
            jsonObject.put("clickEff", clickEfficiency);
            jsonObject.put("saveTime", tsLong);
            jsonObject.put("stagiaire", employes.get(0).getNb());
            jsonObject.put("bts", employes.get(1).getNb());
            jsonObject.put("master", employes.get(2).getNb());
            jsonObject.put("scrum", employes.get(3).getNb());
            jsonObject.put("productOwner", employes.get(4).getNb());



            return jsonObject.toString();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
