package com.example.applicationtest;


import android.util.Log;

import com.example.applicationtest.Amelioration.Amelioration;
import com.example.applicationtest.Amelioration.LeSurvivant;
import com.example.applicationtest.employe.Employe;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import org.json.*;

import java.util.HashMap;
import java.util.Map;

public class GameState {


    //Used to plot the data in the stat fragment

    //counter 0 -> 3600 (1 value per sec)
    public final int max_Xdisplay = 10;
    public static int it_counter=-1;
    private int pointsSize;
    public LineGraphSeries<DataPoint> points;


    public AcceuilActivity ac;

    protected int maxIdle = 3600;

    public double totalloc;

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
        this.ac = ac;
        employes = new HashMap<>();
        availebleEmployes = new HashMap<>();
        points = new LineGraphSeries<DataPoint>();
        pointsSize=0;

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

        ameliorations.put("1", new LeSurvivant());
        ameliorations.put("2", new LeSurvivant());
        ameliorations.put("3", new LeSurvivant());
        ameliorations.put("4", new LeSurvivant());
        ameliorations.put("5", new LeSurvivant());


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

            idleSeconds = System.currentTimeMillis() / 1000 - saveTime;
            Log.i("idesecs", idleSeconds + "");

            loc += locps * idleSeconds;

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void updateValues() {
        locps = 0;
        for (Employe e : employes.values()) {
            locps += e.getNb() * e.getRate();
        }


        Log.i("GameState", "locps end : " + locps);

    }

    public void secondTick() {
        updateGraphData();
        loc += locps;
    }

    public void click() {
        loc += clickEfficiency;
        updateValues();
    }

    public void addAmelioration(Amelioration amm) {


    }


    public void updateGraphData(){

        pointsSize++;
        points.appendData(new DataPoint(it_counter, loc), false, 3600);
        it_counter++;

    }

    public LineGraphSeries<DataPoint> getPoints(){
        return points;
    }

    public int getMax_Xdisplay(){
        return max_Xdisplay;
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
