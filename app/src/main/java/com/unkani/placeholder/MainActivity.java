package com.unkani.placeholder;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    TextView mainTextView;
    Button mainButton;
    TextView adderCount;
    Button adder;
    Button clear;
    Button history;
    Button character;

    String poop = "8";
    private static final String PREFS = "prefs";
    private static final String PLAYERCHARACTER = "";
    private static final String COUNT = "0";

    SharedPreferences mSharedPreferences;

    private int addCount = 0;
    private String counter = "0";

    private static final String TAG = "CounterService";
    private Intent questsIntent;

    private CharStat playerCharacter = new CharStat();


/*    To Retreive:

    Gson gson = new Gson();
    String json = mPrefs.getString("MyObject", "");
    MyObject obj = gson.fromJson(json, MyObject.class);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mainButton = (Button) findViewById(R.id.quests_button);
        mainButton.setOnClickListener(this);
        adderCount = (TextView) findViewById(R.id.adderCount);

        adder = (Button) findViewById(R.id.adder);
        adder.setOnClickListener(this);
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(this);
        history = (Button) findViewById(R.id.History);
        history.setOnClickListener(this);
        character = (Button) findViewById(R.id.characterText);
        character.setOnClickListener(this);


        displayWelcome();
        questsIntent = new Intent(this, CounterService.class);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v == mainButton) {
            Intent questsIntent = new Intent(this, QuestsActivity.class);
            //Pass information into the questsIntent through this!
            questsIntent.putExtra("test", poop);
            startActivity(questsIntent);
        }
        else if (v == adder){
            addCount++;
            adderCount.setText(String.valueOf(addCount));
        }
        else if (v == clear){
            getApplicationContext().getSharedPreferences(PREFS, 0).edit().clear().commit();
            unregisterReceiver(broadcastReceiver);
            stopService(questsIntent);
        }
        else if (v == history){
            Intent historyIntent = new Intent(this, HistoryActivity.class);
            startActivity(historyIntent);
        }
        else if (v == character){
            Intent characterIntent = new Intent(this, CharacterActivity.class);
            String name = mSharedPreferences.getString(PLAYERCHARACTER, "");
            characterIntent.putExtra("charStats", name);

            startActivity(characterIntent);
        }
    }

    public void displayWelcome() {
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        //Read data, or empty if nothing found.
        String name = mSharedPreferences.getString(PLAYERCHARACTER, "");
        //Prepares the Gson object to convert the JSON string into the CharStat object
        Gson gson = new Gson();
        CharStat PC = gson.fromJson(name, CharStat.class);

        if (name.length() > 0) {
            Toast.makeText(this, "Welcome back, " + PC.getName().toString() + "!", Toast.LENGTH_LONG).show();
        }
        else {
            //Creates Alert box
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Hello!");
            alert.setMessage("Looks like this is your first time using this app. What's your name?");
            //EditText to enter name.
            final EditText input = new EditText(this);
            alert.setView(input);
            //an OK button
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                //Method for OK button
                public void onClick(DialogInterface dialog, int whichButton) {
                    String inputName = input.getText().toString();
                    //Initializes the player character
                    playerCharacter.populateStats(inputName, 1, 20, 10, 5, 5, 5, 5, "", "Hero");


                    Gson gson = new Gson();
                    String json = gson.toJson(playerCharacter);
                    SharedPreferences.Editor e = mSharedPreferences.edit();
                    e.putString(PLAYERCHARACTER, json);
                    e.commit();
                    //Welcome
                    Toast.makeText(getApplicationContext(), "Welcome, " + playerCharacter.getName(),
                            Toast.LENGTH_LONG).show();
                }

            });

/*            alert.setNegativeButton("Cancel", new DialogInterface.OnCancelListener() {
                public void onClick(DialogInteraface dialog, int whichbutton) {}
            });*/
            alert.show();
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            updateUI(intent);
        }
    };
    @Override
    public void onResume(){
        super.onResume();
        startService(questsIntent);
        registerReceiver(broadcastReceiver, new IntentFilter(CounterService.BROADCAST_ACTION));
    }
    @Override
    public void onPause(){
        super.onPause();

        /*mSharedPreferences = getApplicationContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(COUNT, counter);

        editor.commit();*/

    }

    @Override
    public void onStop() {
        super.onStop();
        mSharedPreferences = getApplicationContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(COUNT, counter);
/*      Note: Delete this later, in order to save the counter values.
        editor.commit();
*/
        //ble


    }

    private void updateUI(Intent intent){
        counter = mSharedPreferences.getString(COUNT, "0");


        counter = Integer.toString(Integer.valueOf(counter)+Integer.valueOf(intent.getStringExtra("counter")));

//        String time = questsIntent.getStringExtra("time");
        Log.d(TAG, counter);
//        Log.d(TAG, time);
//        TextView txtDateTime = (TextView) findViewById(R.id.txtDateTime);
        TextView txtCounter = (TextView) findViewById(R.id.txtCounter);
//        txtDateTime.setText(time);
        txtCounter.setText(counter);
    }
}
