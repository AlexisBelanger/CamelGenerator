package com.example.applicationtest;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.applicationtest.Amelioration.AmeliorationAdapter;
import com.example.applicationtest.challenge.Challenge;
import com.example.applicationtest.employe.EmployeAdapter;
import com.example.applicationtest.ui.home.HomeFragment;
import com.example.applicationtest.ui.lab.LabFragment;
import com.example.applicationtest.ui.challenge.ChallengeFragment;
import com.example.applicationtest.ui.tavern.TavernFragment;
import com.example.applicationtest.ui.test.TestFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.SEND_SMS;

public class AcceuilActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    private AppBarConfiguration mAppBarConfiguration;

    private GameState gameState;

    private ArrayList<Challenge> challenges = new ArrayList<Challenge>();

    private ArrayList<String> phoneNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.putChallenges();
        if (savedInstanceState != null) {

            gameState = new GameState(this, savedInstanceState.getString("gameState"));
        } else {
            gameState = new GameState(this);
        }

        goTavern();

        findViewById(R.id.accClickbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameState.click();
                gameState.updateValues();
                updateText();
            }
        });

        findViewById(R.id.goTavern).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTavern();
            }
        });
        findViewById(R.id.goChallenge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goChallenge();
            }
        });
        findViewById(R.id.goTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTest();
            }
        });


        findViewById(R.id.goHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });

        findViewById(R.id.goLab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLab();
            }
        });


        updateText();


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameState.secondTick();
                updateText();
            }
        }, 0, 500);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.reset_save:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Do you want to Reset save");
                String[] choice = {"Yes", "No"};
                builder.setItems(choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choice[which].equals("Yes")) {
                            resetGameState();
                            ListView employesList = findViewById(R.id.listTavern);
                            ListView ameliorationList = findViewById(R.id.listLab);

                            if (employesList != null) {
                                ((EmployeAdapter) employesList.getAdapter()).notifyDataSetChanged();
                            }

                            if (ameliorationList != null) {
                                ((AmeliorationAdapter) ameliorationList.getAdapter()).notifyDataSetChanged();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Incredible !!\nNothing happened !", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                return true;

            case R.id.NG:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Do you Want to reset with prestige");
                String[] choice1 = {"Yes", "No"};
                builder1.setItems(choice1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choice1[which].equals("Yes") && gameState.employes.get("inria").getNb() >= 10) {
                            resetGameState();
                            ListView employesList = findViewById(R.id.listTavern);
                            ListView ameliorationList = findViewById(R.id.listLab);

                            if (employesList != null) {
                                ((EmployeAdapter) employesList.getAdapter()).notifyDataSetChanged();
                            }

                            if (ameliorationList != null) {
                                ((AmeliorationAdapter) ameliorationList.getAdapter()).notifyDataSetChanged();
                            }

                            gameState.revenue_multiplier += 5;

                        } else if (gameState.employes.get("inria").getNb() < 10) {
                            Toast.makeText(getApplicationContext(), "Vous n'avez pas assez d'INRIA pour ca", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incredible !!\nNothing happened !", Toast.LENGTH_LONG).show();
                        }
                    }

                });
                builder1.show();
                return true;

            case R.id.Cheat:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Do you want to Cheat\nRemember:\n Gamers don't do cheats");
                String[] choice2 = {"Yes", "No"};
                builder2.setItems(choice2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choice2[which].equals("Yes")) {
                            gameState.addIncome(1000000000);

                        } else {
                            Toast.makeText(getApplicationContext(), "You made the right choice", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder2.show();
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }

    }


    public void updateText() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                TextView loc = findViewById(R.id.accLOC);
                TextView locps = findViewById(R.id.accLOCps);

                loc.setText(utils.prettyfier(gameState.loc));
                locps.setText(utils.prettyfier(gameState.locps * gameState.revenue_multiplier));

            }
        });
    }

    public void goTavern() {

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_tavern) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new TavernFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }

    }

    public void goLab() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_lab) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new LabFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }


    }

    public void goChallenge() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_challenge) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new ChallengeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }


    }


    public void goHome() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_home) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }


    }


    public void goTest() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_test) == null) {

            // Create new fragment and transaction
            Fragment newFragment = new TestFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acceuil, menu);
        return true;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("gameState", gameState.toJSON());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameState = new GameState(this, savedInstanceState.getString("gameState"));


    }

    @Override
    protected void onStop() {
        super.onStop();
        utils.writeTofile(gameState.toJSON(), "save.json", this);


    }


    @Override
    protected void onStart() {
        super.onStart();

        gameState = new GameState(this, utils.ReadFromfile("save.json", this));
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);


        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            Long idls = gameState.idleSeconds;
            Log.i("idsl activity", idls + "");
            builder.setMessage("Pendant votre Absence la corporation a géneré :\n" +
                    utils.prettyfier(idls * gameState.locps) + " LOC\n").setTitle("Bon Retour Parmis Nous");

            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    public void putChallenges() {
        try {
            JSONObject jsonObject = new JSONObject(utils.ReadFromDataFile("challenges", this.getBaseContext()));
            JSONArray jsonArray = jsonObject.getJSONArray("challenges");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonChallenge = jsonArray.getJSONObject(i);
                challenges.add(
                        new Challenge(
                                jsonChallenge.getString("id"),
                                jsonChallenge.getString("nom"),
                                jsonChallenge.getString("description"),
                                jsonChallenge.getInt("recompense"),
                                jsonChallenge.getString("challenge_tag")

                        )
                );


            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void resetGameState() {
        gameState = new GameState(this);
    }


    public void addUser() {
        Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(pickContact, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri contactData = data.getData();
        String num = "";
        String contact = "";
        Cursor c = getContentResolver().query(contactData, null, null, null, null);
        if (c.moveToFirst()) {
            int phoneIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            contact = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME));
            num = c.getString(phoneIndex);
        }
//        ChallengeFragment chall_frag = (ChallengeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_test);

        if (num.matches("^(0|\\+33)[ ]?[1-9]([-. ]?[0-9]{2}){4}$")) {
//            if (!chall_frag.getPhoneNumbers().contains(contact + ";" + num)) {
//                chall_frag.addToPhoneNumbers(contact + ";" + num);
//            }
            if (!getPhoneNumbers().contains(contact + ";" + num)) {
                addToPhoneNumbers(contact + ";" + num);
            }


            //getAdapter().notifyDataSetChanged();
//            Toast.makeText(AcceuilActivity.this, "Numéro ajouté : " + contact, Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(AcceuilActivity.this, "Numéro inconnu : " + num, Toast.LENGTH_SHORT).show();
        }


    }


    public void sendSMS(int score) {
        Log.i("sms", "debut de la fonction");

        requestPermission();

        String message = "Tu as été défié au par un agent de la Camel Corp, pourras tu le battre ? (Score de l'adversaire = " + score + " clicks en 10 s)";
        SmsManager smsManager = SmsManager.getDefault();
        Log.i("sms", "LE SMS EST PARTI    " + phoneNumbers);

        for (String phoneNumber : phoneNumbers) {
            String[] contact_num = phoneNumber.split(";");
            for (String s :
                    contact_num) {
                Log.i("sms", s);

            }
            smsManager.sendTextMessage(contact_num[1], null, message, null, null);
        }
//        Toast.makeText(this,"Message Sent!",Toast.LENGTH_SHORT).show();
        phoneNumbers.clear();

    }


    public void pop_up_envoi(int score) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Envoyer ?");
        String[] choice1 = {"Yes"};
        final AcceuilActivity a = this;
        builder1.setItems(choice1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (choice1[which].equals("Yes")) {
                    a.sendSMS(score);

                }
            }
        });
        builder1.show();

    }

    public void addToPhoneNumbers(String num) {
        this.phoneNumbers.add(num);
    }

    public ArrayList<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    private void requestPermission() {
        if (!(ActivityCompat.checkSelfPermission(this, SEND_SMS) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{SEND_SMS}, 100);
        }
    }


}
