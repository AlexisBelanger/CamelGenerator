package com.example.applicationtest;


import org.json.*;

public class GameState {


    public double codeurs;
    public double loc;
    public double locps;
    public double codeursps;

    public double codeursEfficiency;
    public double clickEfficiency;


    public GameState() {

        codeurs = 0;
        loc = 0;
        locps = 0;
        codeursps = 0;

        codeursEfficiency = 1;
        clickEfficiency = 1;
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

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateValues() {
        locps = codeurs * codeursEfficiency;
    }

    public void secondTick() {
        loc += locps;
        codeurs += codeursps;
    }

    public void click() {
        codeurs += clickEfficiency;
        updateValues();
    }

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("loc", loc);
            jsonObject.put("codeurs", codeurs);
            jsonObject.put("codeursps", codeursps);
            jsonObject.put("locps", locps);
            jsonObject.put("codeursEff", codeursEfficiency);
            jsonObject.put("clickEff", clickEfficiency);

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
