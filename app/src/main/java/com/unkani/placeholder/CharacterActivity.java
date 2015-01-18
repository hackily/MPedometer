package com.unkani.placeholder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

public class CharacterActivity extends ActionBarActivity {


    TextView health;
    TextView mana;
    TextView strength;
    TextView dexterity;
    TextView intelligence;
    TextView vitality;

    ListView mainListView;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tell activity which XML layout to use
        setContentView(R.layout.activity_character);
        //Enable "up" button for navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String charStats = this.getIntent().getExtras().getString("charStats", "1");

        //Prepares the Gson object to convert the JSON string into the CharStat object
        Gson gson = new Gson();
        CharStat PC = gson.fromJson(charStats, CharStat.class);

        health = (TextView) findViewById(R.id.Health);
        health.setText("HP: " + PC.getHP() );
        mana = (TextView) findViewById(R.id.Mana);
        mana.setText("MP: " + PC.getMP() );

        strength = (TextView) findViewById(R.id.Strength);
        strength.setText("Str: " + PC.getStr() );
        dexterity = (TextView) findViewById(R.id.Dexterity);
        dexterity.setText("Dex: " + PC.getDex() );
        intelligence = (TextView) findViewById(R.id.Intelligence);
        intelligence.setText("Int: " + PC.getHP() );
        vitality = (TextView) findViewById(R.id.Vitality);
        vitality.setText("Vit: " + PC.getVit() );


/*        characterText = (TextView) findViewById(R.id.characterText);
        String poop = this.getIntent().getExtras().getString("test", "1");
        Toast.makeText(this, poop, Toast.LENGTH_LONG).show();*/



    }
/*    public void onClick(View v) {

    }*/
}
