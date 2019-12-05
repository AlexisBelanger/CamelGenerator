package com.example.applicationtest;


import android.util.Log;

import org.json.*;

import java.sql.SQLOutput;
import java.sql.Time;

public class GameState {


    public double codeurs;
    public double loc;
    public double locps;
    public double codeursps;


    public double coderMasters;
    public double fortniteWorldCups;
    public double nordVPN;
    public double balkany;

    public double codeursEfficiency;
    public double clickEfficiency;
    public double coderMastersEfficiency;
    public double fwcEfficiency;
    public double opeSpeEfficiency;
    public double balkanyEfficiency;

    public Long idleSeconds;

    public GameState() {

        codeurs = 0;
        loc = 0;
        locps = 0;
        codeursps = 0;

        coderMasters = 0;
        fortniteWorldCups = 0;
        nordVPN = 0;
        balkany = 0;

        codeursEfficiency = 1;
        coderMastersEfficiency = 10 ;
        fwcEfficiency = 100;
        opeSpeEfficiency = 500;
        balkanyEfficiency = 1000;
        clickEfficiency = 1;

        idleSeconds = 0L;
    }

    public GameState(String state) {
        this();
        try {

            JSONObject jsonObject = new JSONObject(state);
            loc = jsonObject.getDouble("loc");
            codeurs = jsonObject.getDouble("codeurs");
            codeursps = jsonObject.getDouble("codeursps");
            locps = jsonObject.getDouble("locps");
            codeursEfficiency = jsonObject.getDouble("codeursEff");
            clickEfficiency = jsonObject.getDouble("clickEff");
            coderMasters = jsonObject.getDouble("codermasters");
            fortniteWorldCups = jsonObject.getDouble("fortniteWC");
            nordVPN = jsonObject.getDouble("nordVPN");
            balkany = jsonObject.getDouble("patrick");

            long saveTime = jsonObject.getLong("saveTime");
            Log.i("create saveTime", saveTime + "");

            idleSeconds = System.currentTimeMillis() / 1000 - saveTime;
            Log.i("idesecs", idleSeconds + "");

            loc += locps * idleSeconds;
            codeurs += codeursps * idleSeconds;

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateValues() {
        locps = codeurs * codeursEfficiency + coderMasters*coderMastersEfficiency +
                nordVPN*opeSpeEfficiency +
                fortniteWorldCups * fwcEfficiency +
                balkany * balkanyEfficiency ;
    }

    public void secondTick() {
        loc += locps;
        codeurs += codeursps;
    }

    public void click() {
        codeurs += clickEfficiency;
        updateValues();
    }

    public void addCoderMaster(){
        coderMasters++;
        updateValues();
    }


    public void addFWC(){
        fortniteWorldCups++;
        updateValues();
    }

    public void addOpeSpe(){
        nordVPN++;
        updateValues();
    }

    public void addBALKANY(){
        balkany++;
        updateValues();
    }


    public void resetArmy(){
        coderMasters = 0;
        fortniteWorldCups = 0;
        nordVPN = 0;
        balkany = 0;
        codeurs = 0;
        loc = 0;
        locps = 0;
        codeursps = 0;
        updateValues();
    }




    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        Long tsLong = System.currentTimeMillis() / 1000;
        try {
            jsonObject.put("loc", loc);
            jsonObject.put("codeurs", codeurs);
            jsonObject.put("codeursps", codeursps);
            jsonObject.put("locps", locps);
            jsonObject.put("codeursEff", codeursEfficiency);
            jsonObject.put("clickEff", clickEfficiency);

            jsonObject.put("codermasters", coderMasters);
            jsonObject.put("fortniteWC", fortniteWorldCups);
            jsonObject.put("nordVPN", nordVPN);
            jsonObject.put("patrick", balkany);

            jsonObject.put("saveTime", tsLong);
            Log.i("save saveTime", tsLong + "");


            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
